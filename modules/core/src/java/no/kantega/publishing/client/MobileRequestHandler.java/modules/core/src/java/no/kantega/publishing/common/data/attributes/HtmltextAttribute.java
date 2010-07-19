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

import no.kantega.commons.util.StringHelper;
import no.kantega.commons.exception.SystemException;
import no.kantega.publishing.common.Aksess;
import no.kantega.publishing.common.exception.InvalidTemplateException;
import no.kantega.publishing.common.data.enums.AttributeProperty;
import no.kantega.publishing.admin.content.behaviours.attributes.UpdateAttributeFromRequestBehaviour;
import no.kantega.publishing.admin.content.behaviours.attributes.UpdateHtmltextAttributeFromRequestBehaviour;
import org.w3c.dom.Element;

import java.util.Map;

/**
 *
 */
public class HtmltextAttribute extends TextAttribute {
    protected boolean isCData = true;
    private String featureSet = "default";
    private String css = "editor.css";

    protected int height  = 350;
    protected int width   = 600;

    public String getRenderer() {
        return "htmltext";
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getFeatureSet() {
        return featureSet;
    }

    public void setConfig(Element config, Map model) throws InvalidTemplateException, SystemException {
        super.setConfig(config, model);
        String h  = config.getAttribute("height");
        if(h != null && !h.trim().equals("")) {
            height = Integer.parseInt(h);
        }
        String w  = config.getAttribute("width");
        if(w != null && !w.trim().equals("")) {
            width = Integer.parseInt(w);
        }

        featureSet = config.getAttribute("featureset");
        if (featureSet == null || featureSet.length() == 0) {
            featureSet = "default";
        }
        css = config.getAttribute("css");
        if (css == null || css.length() == 0) {
            css = "editor.css";
        }
    }

    public String getProperty(String property) {
        String returnValue = value;
        if (value == null || value.length() == 0) {
            return null;
        }

        if (AttributeProperty.HTML.equalsIgnoreCase(property)) {
            // F�rste linje er pga skrivefeil, kan ikke fjernes f�r evt databaser oppdateres
            returnValue = StringHelper.replace(returnValue, "\"" + Aksess.VAR_WEB + "\"/", Aksess.getContextPath() + "/");
            returnValue = StringHelper.replace(returnValue, Aksess.VAR_WEB + "/", Aksess.getContextPath() + "/");
        }
        return returnValue;
    }

    public String getCss() {
        return css;
    }

    public UpdateAttributeFromRequestBehaviour getUpdateFromRequestBehaviour() {
        return new UpdateHtmltextAttributeFromRequestBehaviour();
    }
}
