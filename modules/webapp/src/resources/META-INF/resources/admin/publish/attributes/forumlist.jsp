<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ page import="no.kantega.publishing.common.data.attributes.ForumlistAttribute"%>
<%@ taglib uri="http://www.kantega.no/aksess/tags/commons" prefix="kantega" %>
<%--
  ~ Copyright 2009 Kantega AS
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~    http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>
<%
    ForumlistAttribute attribute = (ForumlistAttribute)request.getAttribute("attribute");
%>
<div class="inputs">
    <select name="${fieldName}" class="fullWidth" tabindex="<%=attribute.getTabIndex()%>">
        <option value="-1"><kantega:label key="aksess.contentproperty.forum.dontuse"/></option>
        <%=attribute.getForumListAsString()%>
    </select>
</div>