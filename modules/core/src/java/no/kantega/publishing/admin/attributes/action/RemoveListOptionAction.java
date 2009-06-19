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

package no.kantega.publishing.admin.attributes.action;

import no.kantega.publishing.common.ao.EditableListAO;

import java.util.Locale;

/**
 * Author: Kristian Lier Seln�s, Kantega AS
 * Date: Jun 12, 2007
 * Time: 10:18:30 AM
 */
public class RemoveListOptionAction extends AbstractListOptionAction {


    protected void doAction(String attributeKey, String value, boolean defaultSelected, Locale locale) throws Exception {
        EditableListAO.deleteOption(attributeKey, value, locale);
    }
}
