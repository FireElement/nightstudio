<%@taglib uri="/spring" prefix="spring"%>
<%@page import="org.springframework.validation.FieldError" %>
<%@page import="org.nightstudio.common.util.constant.RequestConstant" %>
<spring:hasBindErrors name="<%=RequestConstant.PARAM_KEY_COMMAND%>">  
	<%
		for (FieldError error : errors.getFieldErrors()) {
		    if ("".equals(error.getField())) {
		        out.print(error.getDefaultMessage());
		    }
		}
	%>
</spring:hasBindErrors>