package org.nightstudio.common.util.page.message;

import org.nightstudio.common.listener.spring.ApplicationContextAccess;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class MessageUtil {
	public static String m(HttpServletRequest request, String key) {
		return m(request, key, null);
	}
	
	public static String m(HttpServletRequest request, String key, Object[] args) {
		if (request != null) {
			return ApplicationContextAccess.getApplicationContext().getMessage(key, args, request.getLocale());
		} else {
			return ApplicationContextAccess.getApplicationContext().getMessage(key, args, Locale.getDefault());
		}
	}
}
