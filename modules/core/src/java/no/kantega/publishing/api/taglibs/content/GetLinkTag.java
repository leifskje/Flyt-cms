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


import no.kantega.commons.log.Log;
import no.kantega.commons.util.HttpHelper;
import no.kantega.publishing.api.taglibs.content.util.AttributeTagHelper;
import no.kantega.publishing.common.cache.SiteCache;
import no.kantega.publishing.common.data.Content;
import no.kantega.publishing.common.data.ContentIdentifier;
import no.kantega.publishing.common.data.Site;
import no.kantega.publishing.common.exception.ContentNotFoundException;
import no.kantega.publishing.common.service.ContentManagementService;
import no.kantega.publishing.common.util.RequestHelper;
import no.kantega.publishing.common.Aksess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

/**
 *
 */
public class GetLinkTag extends BodyTagSupport{
    private static final String SOURCE = "aksess.GetLinkTag";

    private String collection = null;
    private String contentId  = null;

    private String cssStyle = null;
    private String cssClass = null;
    private String onClick  = null;
    private String accessKey = null;
    private Integer tabIndex  = null;
    private String target = null;
    private String queryParams = null;
    private Content contentObject = null;
    private String title = null;
    private String rel = null;

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setContentid(String contentId) {
         this.contentId = contentId;
    }

    public void setObj(Content obj) {
         this.contentObject = obj;
    }

    public void setStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setCssclass(String cssClass) {
        this.cssClass = cssClass;
    }

    public void setAccesskey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setTabindex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public void setOnclick(String onClick) {
        this.onClick = onClick;
    }

    public void setQueryparams(String queryParams) {
        this.queryParams = queryParams;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public int doStartTag()  throws JspException {
        return EVAL_BODY_TAG;
    }

    public int doAfterBody() throws JspException {
        try {
            String body = bodyContent.getString();
            JspWriter out = bodyContent.getEnclosingWriter();

            String url = null;

            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

            boolean isAdminMode = HttpHelper.isAdminMode(request);

            if (contentObject == null) {
                contentObject = AttributeTagHelper.getContent(pageContext, collection, contentId);
            }

            if (contentObject != null) {
                url = contentObject.getUrl(request);
                HttpSession session = request.getSession(false);

                try {
                    if (url != null && session != null && session.getAttribute("adminMode") != null) {
                        Content current = (Content)request.getAttribute("aksess_this");
                        if (current == null) {
                            // Hent denne siden
                            current = new ContentManagementService(request).getContent(new ContentIdentifier(request), true);
                            RequestHelper.setRequestAttributes(request, current);
                        }
                        if (current.getAssociation().getSiteId() != contentObject.getAssociation().getSiteId()) {
                            Site site = SiteCache.getSiteById(contentObject.getAssociation().getSiteId());
                            List hostnames = site.getHostnames();
                            if (hostnames.size() > 0) {
                                String hostname = (String)hostnames.get(0);
                                String scheme = site.getScheme();
                                int port = request.getServerPort();
                                if (scheme == null) {
                                    scheme = request.getScheme();
                                }
                                url = scheme + "://" + hostname + (port != 80 && port != 443 ? ":" +port : "") + url;
                            }
                        }
                    }
                } catch (ContentNotFoundException e) {
                    // Vi vet ikke hvilken site denne siden tilh�rer, er ikke registrert
                }
            }

            if (url != null) {
                if (queryParams != null) {
                    if ((!queryParams.startsWith("&")) && (!queryParams.startsWith("?"))) {
                        if (url.indexOf("?") == -1) {
                            queryParams = "?" + queryParams;
                        } else {
                            queryParams = "&amp;" + queryParams;
                        }
                    }
                    url = url + queryParams;
                }

                out.print("<a href=\"" + url + "\"");
                if (onClick != null) {
                    out.print(" onClick=\"" + onClick + "\"");
                } else {
                    if (!isAdminMode && (contentObject.isOpenInNewWindow() || Aksess.doOpenLinksInNewWindow() && contentObject.isExternalLink())) {
                        out.print(" onClick=\"window.open(this.href); return false\"");
                    }
                }
                if (cssStyle != null) {
                    out.print(" style=\"" + cssStyle + "\"");
                }
                if (cssClass != null) {
                    out.print(" class=\"" + cssClass + "\"");
                }
                if (accessKey != null) {
                    out.print(" accesskey=\"" + accessKey + "\"");
                }

                if (target == null && isAdminMode) {
                    target = "contentmain";
                }
                
                if (target != null && target.length() > 0) {
                    out.print(" target=\"" + target + "\"");
                }

                if (tabIndex != null) {
                    out.print(" tabindex=\"" + tabIndex + "\"");
                }

                if (title != null) {
                    out.print(" title=\"" + title + "\"");
                }else{
                    out.print(" title=\"" + contentObject.getTitle() + "\"");
                }

                if (rel != null) {
                    out.print(" rel=\"" + rel + "\"");
                }

                out.print(">");
            }

            if(body != null) {
               out.print(body);
            }

            if (url != null) {
                out.print("</a>\n");
            }

        } catch (Exception e) {
            System.err.println(e);
            Log.error(SOURCE, e, null, null);
            throw new JspTagException(SOURCE + ":" + e.getMessage());
        } finally {
            bodyContent.clearBody();
        }

        target = null;
        collection = null;
        cssStyle = null;
        cssClass = null;
        onClick  = null;
        accessKey = null;
        tabIndex  = null;
        queryParams = null;
        contentObject = null;
        title = null;
        rel = null;

        return SKIP_BODY;
     }
}
