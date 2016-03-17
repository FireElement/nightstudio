<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/view.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<ns:css_tag path="yui/reset-fonts-grids"></ns:css_tag>
</head>
<body>
	<div id="doc2">
		<div id="hd">
			<div style="width: 100%; height: 100px; text-indent: 5em; padding-top: 50px; font-size: 200%; background-color: #6ca7bf">
				Welcome to {Template}
			</div>
		</div>
		<div id="bd">
			<div id="main" style="width: 100%; height: 350px;">
				<div style="width: 280px; height: 150px; margin: 0 auto; margin-top: 100px;">
					<form:form method="POST">
					    <jsp:include page="/WEB-INF/view/globalErrors.jsp"></jsp:include>
						<div style="margin: 0 auto; ">
							<form:errors path="name" /><br />
							Name: <form:input path="name" />
						</div>
						<div style="margin: 0 auto; ">
							<form:errors path="passwd" /><br />
							Password: <form:password path="passwd" />
						</div>
						<div style="width: 50px; margin: 0 auto; margin-top: 10px;">
							<input type="submit" value="submit" />
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div id="ft">
			<div style="width: 100%; height: 20px; padding-top: 10px; font-size: 50%; background-color: #6ca7bf; text-align: center">
				Copyright&copy; 2011-{Year} {Template}
			</div>
		</div>
	</div>
	<ns:script_tag path="main"></ns:script_tag>
	<script type="text/javascript">
		adjustMain("main");
	</script>
</body>
</html>