<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.nightstudio.common.util.action.ActionUtil" %>
<%@ page import="org.nightstudio.common.face.action.web.index.LoginAction" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/view/view.jsp"%>
	<title>会话过期</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<ns:css_tag path="error"></ns:css_tag>
</head>
<%
	String from = p.attr("from");
	String href = p.action(LoginAction.class) + "?from=" + from;
%>
<body>
	<div style="margin: 0 auto; margin-top: 50px; padding-top: 40px; width: 400px; height: 90px; text-align: center;" class="error_block">
		会话过期，请<a id="hrefLogin" href="<%=href%>" target="_top">重新登录</a>。<br/>
		3秒后会自动跳转到登录页面。
	</div>
<script type="text/javascript">
window.setTimeout("top.location.href='<%=href%>'", 3000);
</script>
</body>
</html>