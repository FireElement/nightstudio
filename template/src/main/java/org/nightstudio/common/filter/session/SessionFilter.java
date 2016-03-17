package org.nightstudio.common.filter.session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.filter.spi.AbsFilter;
import org.nightstudio.common.util.constant.FilterConstant;
import org.nightstudio.common.util.constant.PathConstant;
import org.nightstudio.common.util.session.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
public class SessionFilter extends AbsFilter {
    private static Log logger = LogFactory.getLog(SessionFilter.class);

    @Override
    protected String[] getIgnorePaths() {
        return FilterConstant.SESSION_FILTER_IGNORE_PATHS;
    }

    @Override
    protected String[] getFilterPaths() {
        return FilterConstant.SESSION_FILTER_PATHS;
    }

    protected boolean checkRequest(ServletRequest request) {
        if (SessionUtil.getUser((HttpServletRequest) request) != null) {
            return true;
        }
        return false;
    }

    protected void handleBadRequest(ServletRequest request,
                                  ServletResponse response) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String contextPath = httpServletRequest.getContextPath();
        if (contextPath.endsWith("/")) {
            contextPath = contextPath.substring(0, contextPath.length() - 1);
        }
        String from = httpServletRequest.getRequestURI();
        if (from.startsWith(contextPath)) {
            from = from.substring(contextPath.length());
        }
        String redirectUrl = httpServletRequest.getContextPath() +
                PathConstant.SESSION_ERROR +
                "?from=" + from;
        ((HttpServletResponse) response).sendRedirect(redirectUrl);
    }
}
