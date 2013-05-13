<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:if test="${productLoad == true}">
		<span>${product.name} </span> <br />
		<p>${product.desc}</p>
		<core:forEach items="${product.attribute.attributes}" var="att">
			<span>${att.attribute} <i>${att.value}</i></span>
			<br />
		</core:forEach>
		<span style="margin-right: 20%;"><button id="buy">Buy</button></span>
</core:if>