<%@ page import="java.util.*" %>
<%@ page import="org.nightstudio.common.util.page.image.ImageUtil" %>
<%@ page import="org.nightstudio.common.util.page.css.CssUtil" %>
<%@ page import="org.nightstudio.common.util.page.script.ScriptUtil" %>
<%@ page import="org.nightstudio.common.util.page.message.MessageUtil" %>
<%@ page import="org.nightstudio.common.util.request.RequestUtil" %>
<%@ page import="org.nightstudio.common.util.session.SessionUtil" %>
<%@ page import="org.nightstudio.common.util.action.ActionUtil" %>
<%@ page import="org.nightstudio.common.face.action.spi.NSWebAction" %>
<%@ page import="org.nightstudio.common.face.action.SessionErrorAction" %>
<%@ page import="org.nightstudio.common.util.datetime.DateTimeUtil" %>
<%@ page import="org.nightstudio.common.bean.*" %>
<%@ page import="org.nightstudio.common.util.constant.*" %>
<%@ page import="com.ns.template.*" %>
<%@taglib uri="/spring" prefix="spring"%>
<%@taglib uri="/spring-form" prefix="form"%>
<%@taglib uri="/c" prefix="c"%>
<%@taglib uri="/ns" prefix="ns"%>
<%
final HttpServletRequest myRequest = request;
class MyPageUtil {
	public String img = ImageUtil.getImagePath(myRequest);
	public String css = CssUtil.getCssPath(myRequest);
	public String js = ScriptUtil.getScriptPath(myRequest);
	public String img(String path) {
		return ImageUtil.getImagePath(myRequest, path);
	}
	public String css(String path) {
		return CssUtil.getCssPath(myRequest, path);
	}
	public String js(String path) {
		return ScriptUtil.getScriptPath(myRequest, path);
	}
	public String m(String key) {
		return MessageUtil.m(myRequest, key);
	}
	public String m(String key, Object[] args) {
		return MessageUtil.m(myRequest, key, args);
	}
	public String attr(String key) {
		return (myRequest.getAttribute(key) != null)? (String) myRequest.getAttribute(key) : "";
	}
	public String[] attrs(String key) {
		return (myRequest.getAttribute(key) != null) ? (String[]) myRequest.getAttribute(key) : null;
	}
	public String s(Object o) {
		return s(o, -1);
	}
	public String s(Object o, int maxLength) {
		return s(o, "&nbsp;", maxLength);
	}
	public String s(Object o, String defaultValue) {
		return s(o, defaultValue, -1);
	}
	public String s(Object o, String defaultValue, int maxLength) {
		String str = (o != null && !"".equals(o.toString()))? o.toString() : defaultValue;
		if (maxLength > 0 && str != null && str.length() > maxLength) {
			str = str.substring(0, maxLength) + "...";
		}
		return str;
	}
	public String v(Object o) {
		if (o == null) {
			return "";
		}
		return o.toString();
	}
    public String action(Class<?> clazz, Object... parameters) {
        return ActionUtil.getActionPath(myRequest, clazz, parameters);
    }
	public Object command() {
		return RequestUtil.getCommand(myRequest);
	}
	/*public boolean isFuncAuth(Function function) {
	    return FunctionUtil.isFunctionAuthenticated(function, myRequest);
	}*/
}
MyPageUtil p = new MyPageUtil();
%>
<%!
public static String getInnerImagePath(HttpServletRequest request) {
	return ImageUtil.getInnerImagePath(request);
}
public static String getInnerImagePath(HttpServletRequest request, String path) {
	return ImageUtil.getInnerImagePath(request, path);
}
public static String getInnerCssPath(HttpServletRequest request) {
	return CssUtil.getInnerCssPath(request);
}
public static String getInnerCssPath(HttpServletRequest request, String path) {
	return CssUtil.getInnerCssPath(request, path);
}
public static String getInnerScriptPath(HttpServletRequest request) {
	return ScriptUtil.getInnerScriptPath(request);
}
public static String getInnerScriptPath(HttpServletRequest request, String path) {
	return ScriptUtil.getInnerScriptPath(request, path);
}
%>