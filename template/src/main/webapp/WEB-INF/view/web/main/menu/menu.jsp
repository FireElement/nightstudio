<%@ page import="org.nightstudio.common.bean.AbsFunction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/view.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<ns:css_tag path="yui/reset-fonts-grids"></ns:css_tag>
<%
String funcCode = (String) request.getAttribute(AbsFunction.REF);
AbsFunction function = Function.getFunction(funcCode);
List<AbsFunction> functions = new ArrayList<AbsFunction>(50);
%>
</head>
<body>
	<div id="doc2" class="yui-t2">
		<div id="bd">
			<!-- background-color: 228/226/175 -->
			<div id="side" class="yui-b" style="background-color: #e4e2af; height: 100%;">
				<ul>
				<% 
					boolean isMenu;
					int level = 1;
					int i;
					if (function != null && function.getSubFunctions() != null) {
					    i = 0;
					    for (AbsFunction tmp : function.getSubFunctions()) {
					        //if (p.isFuncAuth(tmp)) {
					            functions.add(i, tmp);
					            i++;
					        //}
					    }
					    while (!functions.isEmpty()) {
					    	function = functions.remove(0);
					    	if (function == null) {
					    	    out.print("</ul>");
					    	    level--;
					    	    continue;
					    	}
					    	isMenu = false;
					    	if (function.getSubFunctions() != null && !function.getSubFunctions().isEmpty()) {
					    	    i = 0;
							    for (AbsFunction tmp : function.getSubFunctions()) {
							        //if (p.isFuncAuth(tmp)) {
							            functions.add(i, tmp);
							            i++;
							        //}
							    }
					    	    functions.add(i, null);
					    	    isMenu = true;
					    	}
				%>
							<li style="margin-left: <%=10 * level%>px;">
								<% 
									if (isMenu || function.getAction() == null) {
									    out.println(p.m(function.getFuncName()));
									} else {
									    out.println("<a " + 
									            "href='" + p.action(function.getAction()) + "?clear=true'" + 
									            "target='content'>" + 
									            	p.m(function.getFuncName()) + 
									            "</a>");
									}
								%>
							</li>
							<% 
								if (isMenu) {
								    out.print("<ul>");
								    level++;
								}
							%>
				<%
					    }
					} 
				%>
				</ul>
			</div>
			<div id="yui-main">
				<div class="yui-b">
					<iframe id="content" name="content" frameborder="0" style="width: 100%; height: 100%;"> </iframe>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var height = parent.getMainHeight();
		var side = document.getElementById("side");
		side.height = height;
		side.style.height = height + "px";
		var content = document.getElementById("content");
		content.height = height;
		content.style.height = height + "px";
	</script>
</body>
</html>
