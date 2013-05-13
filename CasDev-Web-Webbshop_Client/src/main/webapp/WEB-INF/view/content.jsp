<%@page import="org.casdev.web.model.Product"%>
<%@page import="org.casdev.web.model.collection.ProductCollection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="middle">
	<div id="content">
		<core:if test="${page.type=='START'}">
			<h1 class="page">${page.title}</h1>
		</core:if>
		<core:if test="${page.type=='ARCHIVE'}">
			<h1 class="page">${page.title}</h1>
		</core:if>
		<core:if test="${page.type=='PRODUCT'}">
			<%
				ProductCollection pc = new ProductCollection();
					boolean load = pc.getRandom(7);
					if (load) {

						for (Product product : pc.getProducts()) {
							product.getAttribute().Load();
						}

						request.setAttribute("products", pc.getProducts());
					}
					request.setAttribute("productLoad", load);
			%>
			<h1 class="page">${page.title}</h1>
			<core:if test="${productLoad == true}">
				<div id="productsplacement"></div>
				<core:forEach items="${products}" var="product">
					<br />
					<input type="hidden" value="${product.id}" />
					<span class="product">${product.name} </span>
					<br />
					<p>${product.desc}</p>
					<core:forEach items="${product.attribute.attributes}" var="att">
						<span>${att.attribute} <i>${att.value}</i></span>
						<br />
					</core:forEach>
				</core:forEach>
				<script type="text/javascript">
					$('button#buy').live('click', function(e) {
						e.preventDefault();

						$.ajax({
							type : 'GET',
							url : 'order/buy/' + id,
							success : function(msg) {
								// TODO: update the 'This many products is in your basket'-counter
							}
						})
					});
					$('span.product').live('click', function() {
						var id = $(this).prev('input[type="hidden"]').val();

						alert(id);

						$.ajax({
							type : 'GET',
							url : '${page.url}' + id,
							success : function(msg) {
								alert(msg);
								$('div#productsplacement').html(msg);
							}
						})
					})
				</script>
			</core:if>
		</core:if>
		<core:if test="${page.type=='NEWS'}">
			<h1 class="page">${page.title}</h1>
		</core:if>
		<core:if test="${page.type=='USER'}">
			<h1 class="page">${page.title}</h1>
			<core:choose>
				<core:when test="${useronline == true}">
				</core:when>
				<core:otherwise>
					<h2>Please, log on before you can do anything.</h2>
				</core:otherwise>
			</core:choose>
		</core:if>
		<core:if test="${page.type=='ERROR'}">
			<h1 class="page">${page.title}</h1>
		</core:if>
		<script type="text/javascript">
			jQuery('h1.page').live('click', function(e) {

				var adress = $(this).href;
				if (typeof adress === 'undefined') {
					adress = '${application.home}';
				}

				window.location.href = adress;
			});
		</script>
	</div>
</div>