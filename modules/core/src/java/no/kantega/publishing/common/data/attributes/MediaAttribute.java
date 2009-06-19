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

package no.kantega.publishing.common.data.attributes;

import no.kantega.commons.log.Log;
import no.kantega.publishing.common.util.MultimediaHelper;
import no.kantega.publishing.common.data.Multimedia;
import no.kantega.publishing.common.data.enums.AttributeProperty;
import no.kantega.publishing.common.ao.MultimediaAO;
import no.kantega.publishing.common.Aksess;

/**
 *
 */
public class MediaAttribute extends Attribute {
    int maxWidth = -1;
    int maxHeight = -1;
    String cssClass = null;

    Multimedia mm = null; // Cacher bildet

    protected String filter = null;

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setCssclass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getProperty(String property) {
        String returnValue = value;
        if (value == null || value.length() == 0) {
            return "";
        }

        if (AttributeProperty.URL.equalsIgnoreCase(property)) {
            return Aksess.getContextPath() + "/multimedia.ap?id=" + value;
        } else if (AttributeProperty.HTML.equalsIgnoreCase(property)
                   || AttributeProperty.WIDTH.equalsIgnoreCase(property)
                   || AttributeProperty.HEIGHT.equalsIgnoreCase(property)
                   || AttributeProperty.NAME.equalsIgnoreCase(property)
                   || AttributeProperty.AUTHOR.equalsIgnoreCase(property)
                   || AttributeProperty.DESCRIPTION.equalsIgnoreCase(property)
                   || AttributeProperty.MIMETYPE.equalsIgnoreCase(property)) {
            try {
                int id;

                if (mm == null) {
                    try {
                        id = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        return "";
                    }
                    mm = MultimediaAO.getMultimedia(id);
                    if (mm == null) {
                        return "";
                    }
                }

                if (AttributeProperty.HTML.equalsIgnoreCase(property)) {
                    return MultimediaHelper.mm2HtmlTag(mm, null, maxWidth, maxHeight, cssClass);
                } else if (AttributeProperty.WIDTH.equalsIgnoreCase(property)) {
                    return "" + mm.getWidth();
                } else if (AttributeProperty.HEIGHT.equalsIgnoreCase(property)) {
                    return "" + mm.getHeight();
                } else if (AttributeProperty.AUTHOR.equalsIgnoreCase(property)) {
                    return "" + mm.getAuthor();
                } else if (AttributeProperty.NAME.equalsIgnoreCase(property)) {
                    return "" + mm.getName();
                } else if (AttributeProperty.DESCRIPTION.equalsIgnoreCase(property)) {
                    return "" + mm.getDescription();
                } else if (AttributeProperty.MIMETYPE.equalsIgnoreCase(property)) {
                    return "" + mm.getMimeType().getType();
                }
            } catch (Exception e) {
                Log.error("", e, null, null);
            }
        }
        return returnValue;
    }

    public String getFilter() {
        return filter;
    }

    public String getRenderer() {
        return "media";
    }
}
