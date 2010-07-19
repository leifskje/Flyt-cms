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

import no.kantega.publishing.common.Aksess;
import no.kantega.publishing.common.data.enums.ObjectType;

public class ContentViewStatistics extends NavigationMapEntry {
    private int noPageViews = 0;

    public ContentViewStatistics(int currentId, String title) {
        this.currentId = currentId;
        this.title = title;
    }

    public String getUrl() {
        return Aksess.getContextPath() + "/content.ap?thisId=" + currentId;
    }

    public int getNoPageViews() {
        return noPageViews;
    }

    public void setNoPageViews(int noPageViews) {
        this.noPageViews = noPageViews;
    }

    public int getObjectType() {
        return ObjectType.CONTENT;
    }

    public String getName() {
        return title;
    }

    public String getOwner() {
        return null;
    }

    public String getOwnerPerson() {
        return null;
    }

}
