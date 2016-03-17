package org.nightstudio.common.util.page.image;

import org.nightstudio.common.util.action.ActionUtil;
import org.nightstudio.common.util.constant.PageConstant;
import org.nightstudio.common.util.page.PageUtil;

import javax.servlet.http.HttpServletRequest;

public class ImageUtil extends PageUtil {
	public static String getInnerImagePath(HttpServletRequest request, String path) {
		return getPageResourcePath(request, "", PageConstant.IMAGE_FOLDER, path);
	}
	
	public static String getInnerImagePath(HttpServletRequest request) {
		return getPageResourcePath(request, "", PageConstant.IMAGE_FOLDER);
	}
	
	public static String getImagePath(HttpServletRequest request, String path) {
		return getPageResourcePath(request, ActionUtil.getContextPath(request), PageConstant.IMAGE_FOLDER, path);
	}
	
	public static String getImagePath(HttpServletRequest request) {
		return getPageResourcePath(request, ActionUtil.getContextPath(request), PageConstant.IMAGE_FOLDER);
	}
}
