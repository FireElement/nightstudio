package org.nightstudio.common.util.request;

import org.apache.log4j.Logger;
import org.nightstudio.common.util.constant.RequestConstant;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RequestUtil {
	private static Logger logger = Logger.getLogger(RequestUtil.class);

    public static void setCommand(ModelAndView modelAndView, Object command) {
        modelAndView.addObject(RequestConstant.PARAM_KEY_COMMAND, command);
    }

	public static Object getCommand(HttpServletRequest request) {
		return request.getAttribute(RequestConstant.PARAM_KEY_COMMAND);
	}
	
	public static void dispatchRequest(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	public static String getParameter(HttpServletRequest request, String key) {
		return convertParameter(request.getParameter(key));
	}
	
	public static String convertParameter(String value) {
		if (value != null) {
			value = value.trim();
			try {
				value = new String(value.getBytes(RequestConstant.URL_ENCODING), RequestConstant.PAGE_ENCODING);
				return value;
			} catch (UnsupportedEncodingException e) {
				logger.warn("", e);
			}
		}
		return "";
	}
}
