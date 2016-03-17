package org.nightstudio.common.util.page.css;

import org.nightstudio.common.util.action.ActionUtil;
import org.nightstudio.common.util.constant.PageConstant;
import org.nightstudio.common.util.page.PageUtil;

import javax.servlet.http.HttpServletRequest;

public class CssUtil extends PageUtil {
	public static String getInnerCssPath(HttpServletRequest request, String path) {
		return getPageResourcePath(request, "", PageConstant.CSS_FOLDER, path);
	}
	
	public static String getInnerCssPath(HttpServletRequest request) {
		return getPageResourcePath(request, "", PageConstant.CSS_FOLDER);
	}
	
	public static String getCssPath(HttpServletRequest request, String path) {
		return getPageResourcePath(request, ActionUtil.getContextPath(request), PageConstant.CSS_FOLDER, path);
	}
	
	public static String getCssPath(HttpServletRequest request) {
		return getPageResourcePath(request, ActionUtil.getContextPath(request), PageConstant.CSS_FOLDER);
	}
}
