package org.nightstudio.common.util.page.script;

import org.nightstudio.common.util.action.ActionUtil;
import org.nightstudio.common.util.constant.PageConstant;

import javax.servlet.http.HttpServletRequest;

public class ScriptUtil {
	public static String getInnerScriptPath(HttpServletRequest request, String path) {
		return getInnerScriptPath(request) + "/" + path;
	}
	
	public static String getInnerScriptPath(HttpServletRequest request) {
		return PageConstant.SCRIPT_FOLDER;
	}
	
	public static String getScriptPath(HttpServletRequest request, String path) {
		return getScriptPath(request) + "/" + path;
	}
	
	public static String getScriptPath(HttpServletRequest request) {
		return ActionUtil.getContextPath(request) + PageConstant.SCRIPT_FOLDER;
	}
}
