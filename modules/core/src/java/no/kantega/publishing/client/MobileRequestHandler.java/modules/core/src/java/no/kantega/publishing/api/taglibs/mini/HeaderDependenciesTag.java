/*
 * Copyright 2009 Kantega AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.kantega.publishing.api.taglibs.mini;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class HeaderDependenciesTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        JspWriter out = pageContext.getOut();

        out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+request.getContextPath()+"/admin/css/jquery-ui-1.8.1.custom.css\">");
        out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+request.getContextPath()+"/admin/css/jquery-ui-additions.css\">");
        out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+request.getContextPath()+"/admin/css/miniaksess.css\">");
        out.write("<script type=\"text/javascript\" src=\""+request.getContextPath()+"/admin/js/common.jjs\"></script>");
        out.write("<script type=\"text/javascript\" src=\""+request.getContextPath()+"/admin/js/editcontext.jjs\"></script>");
        out.write("<script type=\"text/javascript\" src=\""+ request.getContextPath()+"/aksess/tiny_mce_3_3_6/tiny_mce.js\"></script>");
    }
}
