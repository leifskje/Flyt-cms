/*
 * Copyright 2009 Kantega AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.kantega.publishing.jobs.cleanup;

import no.kantega.commons.log.Log;
import no.kantega.publishing.common.Aksess;
import no.kantega.publishing.common.ao.ContentAO;
import no.kantega.publishing.common.ao.LinkDao;
import no.kantega.publishing.common.ao.MultimediaUsageDao;
import no.kantega.publishing.common.data.ContentIdentifier;
import no.kantega.publishing.common.data.enums.Event;
import no.kantega.publishing.common.data.enums.ServerType;
import no.kantega.publishing.common.service.impl.EventLog;
import no.kantega.publishing.common.util.database.SQLHelper;
import no.kantega.publishing.common.util.database.dbConnectionFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatabaseCleanupJob  extends QuartzJobBean {
    private static final String SOURCE = "aksess.jobs.DatabaseCleanupJob";

    private LinkDao linkDao;
    private MultimediaUsageDao multimediaUsageDao;

    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws org.quartz.JobExecutionException {

        if (Aksess.getServerType() == ServerType.SLAVE) {
            Log.info(SOURCE, "Job is disabled for server type slave", null, null);
            return;
        }
        Connection c = null;
        try {
            c = dbConnectionFactory.getConnection();

            Calendar cal = null;
            cal = new GregorianCalendar();

            // Slette vedlegg som har blitt liggende igjen
            cal.add(Calendar.DATE, -1);
            PreparedStatement st = c.prepareStatement("delete from attachments where ContentId = -1 AND LastModified < ?");
            st.setTimestamp(1, new java.sql.Timestamp(cal.getTime().getTime()));
            st.execute();

            // Slett trafikk log
            cal = new GregorianCalendar();
            cal.add(Calendar.MONTH, -Aksess.getTrafficLogMaxAge());

            Log.info(SOURCE, "Deleting trafficlog older than " + Aksess.getTrafficLogMaxAge() + " months", null, null);

            st = c.prepareStatement("delete from trafficlog where Time < ?");
            st.setTimestamp(1, new java.sql.Timestamp(cal.getTime().getTime()));
            st.execute();

            // Oppdater antall visninger i loggen
            Log.info(SOURCE, "Updating number of views based on trafficlog", null, null);

            st = c.prepareStatement("update associations set NumberOfViews = (select count(*) from trafficlog where trafficlog.ContentId = associations.ContentId and trafficlog.SiteId = associations.SiteId)");
            st.execute();


            // Slett event log
            cal = new GregorianCalendar();
            cal.add(Calendar.MONTH, -Aksess.getEventLogMaxAge());

            Log.info(SOURCE, "Deleting event log entries older than " + Aksess.getEventLogMaxAge() + " months", null, null);

            st = c.prepareStatement("delete from eventlog where Time < ?");
            st.setTimestamp(1, new java.sql.Timestamp(cal.getTime().getTime()));
            st.execute();

            // Slett ting fra trash som er eldre enn N mnd
            cal = new GregorianCalendar();
            cal.add(Calendar.MONTH, -Aksess.getDeletedItemsMaxAge());

            Log.info(SOURCE, "Deleting deleted items older than " + Aksess.getDeletedItemsMaxAge() + " months", null, null);

            st = c.prepareStatement("delete from deleteditems where DeletedDate < ?");
            st.setTimestamp(1, new java.sql.Timestamp(cal.getTime().getTime()));
            st.execute();

            // Slett knytninger som ikke finnes i trash lenger
            st = c.prepareStatement("delete from associations where IsDeleted = 1 and DeletedItemsId not in (select Id from deleteditems)");
            st.execute();

            // Slett sider som ikke har knytninger lenger
            st = c.prepareStatement("select ContentId from content where ContentId not in (select ContentId from associations)");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ContentIdentifier cid = new ContentIdentifier();
                cid.setContentId(rs.getInt("ContentId"));
                String title = SQLHelper.getString(c, "select title from contentversion where contentId = " + cid.getContentId() + " and IsActive = 1", "title");
                Log.info(SOURCE, "Deleting page " + title + " because it has been in the trash can for over 1 month", null, null);
                EventLog.log("System", null, Event.DELETE_CONTENT_TRASH, title, null);
                linkDao.deleteLinksForContentId(cid.getContentId());
                multimediaUsageDao.removeUsageForContentId(cid.getContentId());
                ContentAO.deleteContent(cid);
            }

            // Slett emneknytning til innhold som ikke finnes
            st = c.prepareStatement("delete from ct2topic where ContentId not in (select ContentId from content)");
            st.execute();

            // Slett lenker i lenkesjekkeren
            st = c.prepareStatement("delete from link where Id not in (select distinct LinkId from linkoccurrence)");
            st.executeUpdate();

        } catch (Exception e) {
            Log.error(SOURCE, e, null, null);
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    Log.error(SOURCE, e, null, null);
                }
            }
        }
    }

    public void setLinkDao(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    public void setMultimediaUsageDao(MultimediaUsageDao multimediaUsageDao) {
        this.multimediaUsageDao = multimediaUsageDao;
    }
}

