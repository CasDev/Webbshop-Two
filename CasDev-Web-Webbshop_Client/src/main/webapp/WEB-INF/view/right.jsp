<%@page import="org.casdev.web.model.Attribute"%>
<%@page import="org.casdev.web.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="org.casdev.web.model.collection.ProductCollection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ProductCollection pc = new ProductCollection();
	boolean scriptsLoaded = pc.getRandom(2);
	if (scriptsLoaded) {
		List<Product> list = pc.getProducts();

		for (Product p : list) {

			p.getAttribute().Load();

		}

		request.setAttribute("adverticement", list);
	}
	request.setAttribute("addLoaded", scriptsLoaded);
%>
<div id="right">
	<div class="add one">
		<p>
			<core:if test="${addLoaded == true}">
				<core:forEach items="${adverticement}" var="add">
					<br />
					<span>${add.name} </span>
					<br />
					<core:forEach items="${add.attribute.attributes}" var="att">
						<span>${att.attribute} <i>${att.value}</i></span>
						<br />
					</core:forEach>
					<br />
				</core:forEach>
			</core:if>
		</p>
		<br />
	</div>
</div>