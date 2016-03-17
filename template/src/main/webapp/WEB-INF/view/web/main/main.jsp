<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.nightstudio.common.bean.AbsFunction" %>
<%@ page import="org.nightstudio.common.bean.AdminUser" %>
<%@ page import="org.nightstudio.common.face.action.web.main.LogoutAction" %>
<%@ page import="org.nightstudio.common.face.action.web.main.menu.MenuAction" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/view.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Main</title>
<ns:css_tag path="yui/reset-fonts-grids"></ns:css_tag>
<%
AdminUser user = SessionUtil.getUser(request);
List<AbsFunction> functions = Function.root.getSubFunctions();
%>
</head>
<body>
	<div id="doc2">
		<div id="hd" style="background-color: #6ca7bf;">
			<div style="width: 100%; height: 40px; text-indent: 3em; padding-top: 10px; font-size: 150%;">
				Hello, <%=user.getEmail()%>
			</div>
			<div style="width: 100%; height: 30px; text-indent: 5em; margin-top: 10px;">
				<% 
					for (AbsFunction function : functions) {
				%>
						<a href="<%=p.action(MenuAction.class, Function.REF, function.getFuncCode()) %>" target="menu"><%=p.m(function.getFuncName())%></a>&nbsp;
				<% 
					}
				%>
				<div style="float: right; padding-right: 20px;">
					<a href="<%=p.action(LogoutAction.class) %>">Logout</a>
				</div>
			</div>
		</div>
		<div id="bd">
			<iframe id="menu" name="menu" frameborder="0" style="width: 100%; height: 100%;">
			</iframe>
		</div>
		<div id="ft">
			<div style="width: 100%; height: 20px; padding-top: 10px; font-size: 50%; background-color: #6ca7bf; text-align: center">
				Copyright&copy; 2011-2012 LBS BIZ team 
			</div>
		</div>
	</div>
	<ns:script_tag path="main"></ns:script_tag>
	<script type="text/javascript">
		adjustMain("menu");
	</script>
</body>
</html>
