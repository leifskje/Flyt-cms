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

package no.kantega.publishing.common.data;

import no.kantega.publishing.common.data.enums.TrafficOrigin;

import java.util.Date;

/**
 * User: Anders Skar, Kantega AS
 * Date: Feb 18, 2008
 * Time: 2:08:32 PM
 */
public class TrafficLogQuery {
    private ContentIdentifier cid = null;
    private boolean includeSubPages = false;
    private int siteId = -1;
    private Date start = null;
    private Date end = null;
    private int trafficOrigin = TrafficOrigin.ALL_USERS;

    public ContentIdentifier getCid() {
        return cid;
    }

    public void setCid(ContentIdentifier cid) {
        this.cid = cid;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getTrafficOrigin() {
        return trafficOrigin;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public void setTrafficOrigin(int trafficOrigin) {
        this.trafficOrigin = trafficOrigin;
    }

    public boolean isIncludeSubPages() {
        return includeSubPages;
    }

    public void setIncludeSubPages(boolean includeSubPages) {
        this.includeSubPages = includeSubPages;
    }
}
