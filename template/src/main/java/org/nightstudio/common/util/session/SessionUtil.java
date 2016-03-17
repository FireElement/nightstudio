package org.nightstudio.common.util.session;

import org.nightstudio.common.bean.AdminUser;
import org.nightstudio.common.bean.Pagination;
import org.nightstudio.common.util.constant.SessionConstant;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    public static AdminUser getUser(HttpServletRequest request) {
        return (AdminUser) getAttribute(request, SessionConstant.SESSION_KEY_USER);
    }

    public static void setUser(HttpServletRequest request, AdminUser user) {
        setAttribute(request, SessionConstant.SESSION_KEY_USER, user);
    }

    public static void removeUser(HttpServletRequest request) {
        removeAttribute(request, SessionConstant.SESSION_KEY_USER);
    }

	public static Object getAttribute(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
	public static void setAttribute(HttpServletRequest request, String key, Object obj) {
		request.getSession().setAttribute(key, obj);
	}
	
	public static void removeAttribute(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}
	
	public static Pagination getPagination(HttpServletRequest request) {
		return (Pagination) getAttribute(request, SessionConstant.SESSION_KEY_PAGINATION);
	}
	
	public static void setPagination(HttpServletRequest request, Pagination pagination) {
		setAttribute(request, SessionConstant.SESSION_KEY_PAGINATION, pagination);
	}
	
	public static void removePagination(HttpServletRequest request) {
		removeAttribute(request, SessionConstant.SESSION_KEY_PAGINATION);
	}
}
