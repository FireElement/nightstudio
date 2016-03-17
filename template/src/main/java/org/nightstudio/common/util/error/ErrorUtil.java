package org.nightstudio.common.util.error;

import org.nightstudio.common.listener.spring.ApplicationContextAccess;
import org.nightstudio.common.util.constant.RequestConstant;
import org.nightstudio.common.util.constant.ViewConstant;
import org.nightstudio.common.util.exception.sys.NSException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ErrorUtil {
    public static ModelAndView getErrorView(NSException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ViewConstant.ERROR_VIEW);
        setError(modelAndView, e);
        return modelAndView;
    }

    public static void setError(ModelAndView modelAndView, NSException exception) {
       modelAndView.addObject(RequestConstant.PARAM_KEY_ERROR, exception);
    }

    public static NSException getError(HttpServletRequest request) {
        return (NSException) request.getAttribute(RequestConstant.PARAM_KEY_ERROR);
    }

    public static String getErrorMessage(HttpServletRequest request) {
        NSException e = ErrorUtil.getError(request);
        return getErrorMessage(e, request);
    }

    public static String getErrorMessage(NSException e) {
        return getErrorMessage(e, null);
    }

    public static String getErrorMessage(NSException e, HttpServletRequest request) {
        if (e == null) {
            return "";
        } else {
            try {
                if (request != null) {
                    return ApplicationContextAccess.getApplicationContext().getMessage(e.getErrorCode().getValue(), e.getArgs(), request.getLocale());
                } else {
                    return ApplicationContextAccess.getApplicationContext().getMessage(e.getErrorCode().getValue(), e.getArgs(), Locale.getDefault());
                }
            } catch (Throwable e1) {
                return e.getErrorCode().getValue();
            }
        }
    }
}
