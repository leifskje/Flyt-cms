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

package no.kantega.publishing.admin.content.behaviours.attributes;

import no.kantega.commons.client.util.RequestParameters;
import no.kantega.publishing.admin.content.util.AttributeHelper;
import no.kantega.publishing.common.data.Content;
import no.kantega.publishing.common.data.attributes.Attribute;
import no.kantega.publishing.common.data.attributes.FileAttribute;
import org.springframework.web.multipart.MultipartFile;

public class UpdateFileAttributeFromRequestBehaviour implements UpdateAttributeFromRequestBehaviour {
    public void updateAttribute(RequestParameters param, Content content, Attribute attribute) {
        String inputField = AttributeHelper.getInputFieldName(attribute.getName());

        FileAttribute fattr = (FileAttribute)attribute;
        MultipartFile value = param.getFile(inputField);
        if (value != null) {
            fattr.setImportFile(value);
        } else {
            int delete = param.getInt("delete_" + inputField);
            if (delete == 1) {
                fattr.setDeleteAttachment(true);
            }
        }
    }
}
