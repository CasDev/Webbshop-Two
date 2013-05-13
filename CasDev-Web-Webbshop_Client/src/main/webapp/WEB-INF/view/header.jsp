<%@page import="org.casdev.web.model.collection.MenuTree"%>
<%@page import="org.casdev.web.model.attribute.PageType"%>
<%@page import="org.casdev.web.model.MenuItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MenuTree menu = new MenuTree();
	menu.setLevel((byte) 2);
	boolean loaded = menu.Load();
	if (loaded) {
		request.setAttribute("load", true);
	} else {
		request.setAttribute("load", false);
	}
%>
<div id="top">
	<core:if test="${application.logo == true}">
		<div class="loggo">
			<img width="100px" height="25px" title="Logo"
				href="${application.logoUrl}" id="logo" />
		</div>
	</core:if>
	<h1 class="title">${application.applicationName}</h1>
	<div id="submenu">
		<core:if test="${load == true}">
			<ul id="submenu">
				<core:forEach items="${menu.menu}" var="menu">
					<li class="meny-item"><a href="${menu.url}">${menu.title}</a></li>
				</core:forEach>
			</ul>
		</core:if>
	</div>
</div>
