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

package no.kantega.search.criteria;

import no.kantega.search.index.Fields;

/**
 * Date: Jan 8, 2009
 * Time: 8:09:09 AM
 *
 * @author Tarje Killingberg
 */
public class ContentTemplateCriterion extends IntTermArrayCriterion {

    private static final String SOURCE = ContentTemplateCriterion.class.getName();


    public ContentTemplateCriterion(int contentTemplate) {
        super(contentTemplate);
    }

    public ContentTemplateCriterion(int[] contentTemplateArray) {
        super(contentTemplateArray);
    }

    @Override
    protected String getField() {
        return Fields.CONTENT_TEMPLATE_ID;
    }

}
