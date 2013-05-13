<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:choose>
	<core:when test="${useronline == false}">
		<input type="text" value="Username" id="txtUsername"
			style="width: 40%;" />
		<br />
		<input type="password" value="password" id="txtPassword"
			style="width: 40%;" />
		<br />
		<button type="submit" id="smtLogin" href="${login.url}" value="Login">Log
			In</button>
		<script type="text/javascript">
			$('button#smtLogin').live('click', function() {
			{
				var username = $('input#txtUsername').val();
				var password = $('input#txtPassword').val();
				var basicAuth = 'Basic ' + btoa(username + ':' + password);

				$.ajax({
					type : 'GET',
					url : '${login.url}',
					headers : {
						'Authentication' : basicAuth
					},
					success : function(msg) {
						var i = 0;

						if (i == 0) {
							$('div#user').html(msg);
							i = i + 1;
						}
					}
				})
			});
		</script>
	</core:when>
	<core:otherwise>
		<span style="">Hello ${user.username}</span>
		<br />
		<button type="submit" id="smtLogout" href="${login.url}" value="Login">Log
			${user.username} Out</button>
		<script type="text/javascript">
			$('button#smtLogout').live('click', function(e) {
				e.preventDefault();

				$.ajax({
					type : 'GET',
					url : '${login.url}',
					success : function(msg) {
						var i = 0;

						if (i == 0) {
							$('div#user').html(msg);
							i = i + 1;
						}
					}
				})
			});
		</script>
	</core:otherwise>
</core:choose>