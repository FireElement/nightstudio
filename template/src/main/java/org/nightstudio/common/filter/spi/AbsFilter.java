package org.nightstudio.common.filter.spi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caoxuezhu01 on 14-10-24.
 */
public abstract class AbsFilter implements Filter {
    private static Log logger = LogFactory.getLog(AbsFilter.class);

    protected abstract String[] getIgnorePaths();

    protected abstract String[] getFilterPaths();

    protected abstract boolean checkRequest(ServletRequest request);

    protected abstract void handleBadRequest(ServletRequest request,
                                             ServletResponse response) throws IOException, ServletException;

    public void init(FilterConfig arg0) throws ServletException {

    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (request == null) {
            handleBadRequest(request, response);
            return;
        }
        if (!(request instanceof HttpServletRequest)) {
            handleBadRequest(request, response);
            return;
        }
        if (!(response instanceof HttpServletResponse)) {
            handleBadRequest(request, response);
            return;
        }
        if (!checkRequest(request)) {
            if (!ignorePath((HttpServletRequest) request)) {
                handleBadRequest(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean ignorePath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        for (String ignorePath : getIgnorePaths()) {
            if (uri.endsWith(ignorePath)) {
                return true;
            }
        }
        String contextPath = request.getContextPath();
        if (contextPath.endsWith("/")) {
            contextPath = contextPath.substring(0, contextPath.length() - 1);
        }
        for (String path : getFilterPaths()) {
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            path = contextPath + "/" + path;
            if (uri.startsWith(path)) {
                return false;
            }
        }
        return true;
    }
}
