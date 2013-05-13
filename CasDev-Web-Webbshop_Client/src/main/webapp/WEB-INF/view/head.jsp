<%@page import="org.casdev.web.model.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Page p = (Page) request.getAttribute("page");
	boolean scriptsLoaded = p.getScripts().Load();
	request.setAttribute("scriptsLoaded", scriptsLoaded);
	if (scriptsLoaded) {
		request.setAttribute("page", p);
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<core:if test="${scriptsLoaded == true}">
	<core:forEach items="${page.scripts.scripts}" var="script">
		<script type="text/javascript" src="${script}"></script>
	</core:forEach>
</core:if>
<title>${application.applicationName} | ${page.title}</title>
</head>