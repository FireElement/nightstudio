package org.nightstudio.common.util.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
public class ActionUtil {
    private static Log logger = LogFactory.getLog(ActionUtil.class);
    private static Map<Class<?>, String> viewPathMap = new HashMap<Class<?>, String>();

    public static String getContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    public static String getViewPath(Class<?> clazz) {
        if (viewPathMap.get(clazz) == null) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(RequestMapping.class)) {
                    RequestMapping mapping = (RequestMapping) annotation;
                    String[] values = mapping.value();
                    if (values != null && values[0] != null) {
                        for (String value : values) {
                            if (value.endsWith(".do")) {
                                value = value.substring(0, value.length() - 3);
                                viewPathMap.put(clazz, value);
                            }
                        }
                    }
                }
            }
        }
        String path;
        if ((path = viewPathMap.get(clazz)) == null) {
            logger.warn("could not get path for action '" + clazz.getName() + "'");
            return "";
        } else {
            return path;
        }
    }

    public static String getActionPath(HttpServletRequest request, Class<?> clazz, Object... parameters) {
        String result = getContextPath(request) + getActionPath(clazz);
        if (parameters != null && parameters.length > 0) {
            StringBuilder builder = new StringBuilder(100);
            for (int i = 0; i < parameters.length; i += 2) {
                if (i + 1 < parameters.length) {
                    builder.append(parameters[i]).append("=").append(parameters[i + 1]).append("&");
                }
            }
            if (builder.length() > 0) {
                result = result + "?" + builder.substring(0, builder.length() - 1);
            }
        }
        return result;
    }

    public static String getActionPath(Class<?> clazz) {
        return getViewPath(clazz) + ".do";
    }
}
