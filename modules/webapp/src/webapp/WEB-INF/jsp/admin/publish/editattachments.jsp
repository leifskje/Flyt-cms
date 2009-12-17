<%@ page import="no.kantega.publishing.common.data.enums.ExpireAction" %>
<%@ page import="no.kantega.publishing.admin.content.InputScreenRenderer" %>
<%@ page import="no.kantega.publishing.common.data.enums.AttributeDataType" %>
<%@ page import="no.kantega.publishing.common.data.Content" %>
<%@ page import="no.kantega.publishing.common.data.Attachment" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="no.kantega.publishing.common.Aksess" %>
<%@ page import="no.kantega.commons.util.FormatHelper" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" uri="http://www.kantega.no/aksess/tags/admin" %>
<%@ taglib prefix="kantega" uri="http://www.kantega.no/aksess/tags/commons" %>
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

<kantega:section id="title">
    <kantega:label key="aksess.edit.title"/>
</kantega:section>

<kantega:section id="content">
<script language="Javascript" type="text/javascript">
    var hasSubmitted = false;

    function initialize() {
        // Do nothing
    }

    function saveContent(status) {
        if (validatePublishProperties()) {
            if (!hasSubmitted) {
                hasSubmitted = true;
                document.myform.status.value = status;
                document.myform.submit();
            }
        }
    }

    function addAttachment() {
        updateAttachment(-1);
    }

    function updateAttachment(id) {
        var title;
        if (id == -1) {
            title = '<kantega:label key="aksess.attachment.add"/>';
        } else {
            title = '<kantega:label key="aksess.attachment.update"/>';
        }
        ModalWindow.open({title:title, iframe:true, href: "popups/AddAttachment.action?attachmentId=" + id,width: 380, height:250});
    }

    function deleteAttachment(id) {
        if (confirm("<kantega:label key="aksess.attachments.confirmdelete"/>")) {
            window.location.href = "DeleteAttachment.action?attachmentId=" + id;
        }
    }

</script>
<form name="myform" action="SaveAttachments.action" method="post" enctype="multipart/form-data">
    <table border="0" cellspacing="0" cellpadding="0" width="600">
        <tr class="tableHeading">
            <td><strong><kantega:label key="aksess.attachments.attachment"/></strong></td>
            <td><strong><kantega:label key="aksess.attachments.size"/></strong></td>
            <td><strong><kantega:label key="aksess.attachments.lastmodified"/></strong></td>
            <td>&nbsp;</td>
        </tr>
    <%
        List attachments = (List)request.getAttribute("attachments");

        for (int i = 0; i < attachments.size(); i++) {
            Attachment a = (Attachment)attachments.get(i);

            String modifiedDate = "";
            Date d = a.getLastModified();
            DateFormat df = new SimpleDateFormat(Aksess.getDefaultDateFormat());

            try {
                modifiedDate = df.format(d);
            } catch (NumberFormatException e) {

            }
            String filename = a.getFilename();
            if (filename.length() > 50) {
               filename = filename.substring(0, 47) + "...";
            }
    %>
            <tr class="tableRow<%=(i%2)%>">
                <td><a href="${pageContext.request.contextPath}/attachment.ap?id=<%=a.getId()%>"><%=filename%></a></td>
                <td><%=FormatHelper.formatSize(a.getSize())%></td>
                <td><%=modifiedDate%></td>
                <td align="right">
                    <a href="Javascript:updateAttachment(<%=a.getId()%>)" class="button"><span class="edit"><kantega:label key="aksess.button.erstattfil"/></a></span>
                    <a href="Javascript:deleteAttachment(<%=a.getId()%>)" class="button"><span class="delete"><kantega:label key="aksess.button.deleteattachment"/></a></span>
                </td>
            </tr>
    <%
        }
    %>
            <tr>
                <td colspan="4" align="right">
                     <a href="Javascript:addAttachment()" class="button"><span class="new"><kantega:label key="aksess.button.nyttvedlegg"/></a></span>
                </td>
            </tr>
    </table>

    <%
        
        if (attachments.size() == 0) {
    %>
            <div class=helpText><kantega:label key="aksess.attachments.hjelp"/></div>
    <%
        }
    %>

    <input type="hidden" name="status" value="">
    <input type="hidden" name="action" value="">
    <input type="hidden" name="currentId" value="${currentContent.id}">
    <input type="hidden" name="isModified" value="${currentContent.modified}">
</form>
</kantega:section>
<%@ include file="../layout/publishLayout.jsp" %>


