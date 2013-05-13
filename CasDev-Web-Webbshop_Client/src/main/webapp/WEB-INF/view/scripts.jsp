<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	function loadLink(element) {
		alert(element);

		var adress = $(this).href;
		if (adress === null) {
			adress = '${application.home}';
		}

		alert(adress);
		window.location.href = adress;
	}
</script>