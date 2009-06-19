<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="no.kantega.publishing.common.Aksess,
                 java.util.Date,
                 java.text.DateFormat,
                 java.text.SimpleDateFormat"%>
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
    DateFormat df = new SimpleDateFormat("yyyy");
    String year = df.format(new Date());
%>
<html>
<head>
<title>Aksess Publiseringsystem - Versjon <%=Aksess.getVersion()%></title>
<link rel="stylesheet" type="text/css" href="../login/login.css">
</head>
<body>
    <img src="../login/bitmaps/blank.gif" width="200" height="10"><br>
    <table border="0" cellspacing="0" cellpadding="0" width="215" align="center">
        <tr>
            <td width="1" rowspan="3" class="frame"><img src="../login/bitmaps/blank.gif" width="1" height="1"></td>
            <td width="210" class="frame"><img src="../login/bitmaps/blank.gif" width="1" height="1"></td>
            <td width="1" rowspan="3" class="frame"><img src="../login/bitmaps/blank.gif" width="1" heigth="1"></td>
            <td width="2" rowspan="3" class="shadow" valign="top"><img src="../login/bitmaps/corner.gif" width="2" heigth="2"></td>
        </tr>
        <tr>
            <td class="box">
                <table border="0" cellspacing="2" cellpadding="2" align="center" width="210">
                    <tr>
                        <td class="inpHeading"><b>Aksess Publisering <%=Aksess.getVersion()%></b></td>
                    </tr>
                    <tr>
                        <td align="center">Copyright <a href="http://www.kantega.no" target="_blank">Kantega</a> <%=year%></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td class="frame"><img src="../login/bitmaps/blank.gif" width="1" height="1"></td>
        </tr>
        <tr>
            <td colspan="4" class="shadow"><img src="../login/bitmaps/corner.gif" width="2" height="2"></td>
        </tr>
    </table>
</body>
</html>