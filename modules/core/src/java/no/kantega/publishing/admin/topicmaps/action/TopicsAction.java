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
package no.kantega.publishing.admin.topicmaps.action;

import no.kantega.publishing.admin.viewcontroller.AdminController;
import no.kantega.publishing.topicmaps.data.TopicMap;
import no.kantega.publishing.common.service.TopicMapService;
import no.kantega.commons.client.util.RequestParameters;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class TopicsAction extends AdminController {
    private String view;

    @Override
    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();

        RequestParameters param = new RequestParameters(request);
        int topicMapId = param.getInt("topicMapId");

        TopicMapService topicMapService = new TopicMapService(request);
        List<TopicMap> topicMaps = topicMapService.getTopicMaps();
        if (topicMapId == -1 && topicMaps.size() >= 1) {
            topicMapId = topicMaps.get(0).getId();
        }

        model.put("topicMapId", topicMapId);
        model.put("topicMaps", topicMaps);

        return new ModelAndView(view, model);
    }

    public void setView(String view) {
        this.view = view;
    }
}
