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

package no.kantega.publishing.admin.content.action;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import no.kantega.publishing.common.service.lock.LockManager;
import no.kantega.publishing.common.Aksess;
import no.kantega.commons.client.util.RequestParameters;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class ListContentExpirationAction extends AbstractController {
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();

        RequestParameters param = new RequestParameters(request);
        DateFormat df = new SimpleDateFormat(Aksess.getDefaultDateFormat());

        Date from = param.getDate("from_date", Aksess.getDefaultDateFormat());
        Date end  = param.getDate("end_date", Aksess.getDefaultDateFormat());

        if (from == null) {
            from = new Date();
        }

        if (end == null) {
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.DATE, 30);
            end = cal.getTime();
        }

        model.put("expireFromDateStr", df.format(from));
        model.put("expireToDateStr", df.format(end));

        model.put("expireFromDate", from);
        model.put("expireToDate", end);

        return new ModelAndView("/WEB-INF/jsp/admin/contentexpiration/listcontentexpiration.jsp", model);
    }
}
