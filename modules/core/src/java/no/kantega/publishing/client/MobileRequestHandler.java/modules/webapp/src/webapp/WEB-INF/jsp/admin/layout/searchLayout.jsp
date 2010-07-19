<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kantega" uri="http://www.kantega.no/aksess/tags/commons" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="iso-8859-1" %>
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title><kantega:getsection id="title"/></title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/login/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/login/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/admin/css/default.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/admin/css/search.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/aksess/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/js/jquery.dimensions.pack.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/js/jquery.interface.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/js/jquery.lazyload.mini.js"></script>

    <kantega:getsection id="head"/>
</head>

<body>
    <div id="Content" class="search">
        <kantega:getsection id="body"/>
    </div>
</body>
</html>
