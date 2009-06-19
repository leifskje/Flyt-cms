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

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspTagException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import no.kantega.publishing.common.data.enums.AttributeDataType;
import no.kantega.publishing.common.data.enums.AttributeProperty;
import no.kantega.publishing.common.data.Content;
import no.kantega.publishing.api.taglibs.content.util.AttributeTagHelper;
import no.kantega.publishing.security.SecuritySession;
import no.kantega.commons.exception.NotAuthorizedException;
import no.kantega.commons.log.Log;

/**
 *
 */
public class GetAttributeTag extends TagSupport {
    private static final String SOURCE = "aksess.GetAttributeTag";

    private String name = null;
    private String contentId = null;
    private String collection = null;
    private String cssClass = null;
    private String format = null;
    private String property = AttributeProperty.HTML;
    private String transform = null;
    private String defaultValue = null;
    private String contentDisposition = null;
    private Content contentObject = null;

    private boolean inheritFromAncestors = false;

    private int height = -1;
    private int width  = -1;
    private int maxlen = -1;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setCollection(String collection) {
        if (collection != null && collection.length() == 0) {
            collection = null;
        }
        this.collection = collection;
    }

    public void setContentid(String contentId) {
        if (contentId != null && contentId.length() == 0) {
            contentId = null;
        }
        this.contentId = contentId;
    }

    public void setObj(Content obj) {
        this.contentObject = obj;
    }

    public void setCssclass(String cssClass) {
        this.cssClass = cssClass;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMaxlength(int maxlen) {
        this.maxlen = maxlen;
    }

    public void setTransform(String transform) {
        this.transform = transform;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setDefaultvalue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setInheritfromancestors(Boolean inheritFromAncestors) {
        this.inheritFromAncestors = inheritFromAncestors;
    }

    public void setContentdisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public int doStartTag() throws JspException {
        JspWriter out;

        try {
            out = pageContext.getOut();
            try {
                GetAttributeCommand cmd = new GetAttributeCommand();
                cmd.setName(name);
                cmd.setProperty(property);
                cmd.setMaxLength(maxlen);
                cmd.setAttributeType(AttributeDataType.CONTENT_DATA);
                cmd.setFormat(format);
                cmd.setCssClass(cssClass);
                cmd.setWidth(width);
                cmd.setHeight(height);
                cmd.setContentDisposition(contentDisposition);

                if (contentObject == null) {
                    contentObject = AttributeTagHelper.getContent(pageContext, collection, contentId);
                }
                String result = AttributeTagHelper.getAttribute(contentObject, cmd, inheritFromAncestors);

                if (defaultValue != null && (result == null || result.length() == 0)) {
                    result = defaultValue;
                }

                if (result != null) {
                    if (transform != null) {
                        if ("lowercase".equalsIgnoreCase(transform)) result = result.toLowerCase();
                        if ("uppercase".equalsIgnoreCase(transform)) result = result.toUpperCase();
                    }
                    out.write(result);
                }
            } catch (NotAuthorizedException e) {
                HttpServletRequest  request  = (HttpServletRequest)pageContext.getRequest();
                HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
                SecuritySession session = SecuritySession.getInstance(request);
                if (session.isLoggedIn()) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                } else {
                    // G� til loginside
                    session.initiateLogin(request, response);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            Log.error(SOURCE, e, null, null);
            throw new JspTagException(SOURCE + ":" + e.getMessage());
        }

        return SKIP_BODY;
    }


    public int doEndTag() throws JspException {
        name = null;
        contentId = null;
        collection = null;
        cssClass = null;
        format = null;
        property = AttributeProperty.HTML;
        transform = null;
        defaultValue = null;
        height = -1;
        width  = -1;
        maxlen = -1;
        inheritFromAncestors = false;
        contentDisposition = null;
        contentObject = null;

        return EVAL_PAGE;
    }
}
