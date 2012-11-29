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

package no.kantega.publishing.api.taglibs.content;

import no.kantega.publishing.common.data.Content;
import no.kantega.publishing.common.data.attributes.Attribute;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Determines whether an attribute exists and has value.
 * @see AttributeNotExistsTag
 */
public class AttributeExistsTag extends AbstractAttributeConditionTag {


    @Override
    protected boolean evaluateCondition(Content content, Attribute attribute) {
        return attribute != null && isNotBlank(attribute.getValue());
    }
}
