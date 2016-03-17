package org.nightstudio.common.filter.token;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.business.TokenBiz;
import org.nightstudio.common.filter.spi.AbsFilter;
import org.nightstudio.common.util.constant.FilterConstant;
import org.nightstudio.common.util.constant.PathConstant;
import org.nightstudio.common.util.constant.RequestConstant;
import org.nightstudio.common.util.exception.sys.NSException;
import org.nightstudio.common.util.spring.SpringUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
public class TokenFilter extends AbsFilter {
    private static Log logger = LogFactory.getLog(TokenFilter.class);
    private static TokenBiz tokenBiz = null;
    private static Object lock = new Object();

    private static TokenBiz getTokenBiz() {
        if (tokenBiz == null) {
            synchronized (lock) {
                if (tokenBiz == null) {
                    try {
                        tokenBiz = (TokenBiz) SpringUtil.getBeanOfType(TokenBiz.class);
                    } catch (NSException e) {
                        logger.warn("", e);
                    }
                }
            }
        }
        return tokenBiz;
    }

    @Override
    protected String[] getIgnorePaths() {
        return FilterConstant.TOKEN_FILTER_IGNORE_PATHS;
    }

    @Override
    protected String[] getFilterPaths() {
        return FilterConstant.TOKEN_FILTER_PATHS;
    }

    protected boolean checkRequest(ServletRequest request) {
        String token = request.getParameter(RequestConstant.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        TokenBiz tokenBiz = getTokenBiz();
        try {
            tokenBiz.check(token);
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    protected void handleBadRequest(ServletRequest request,
                                  ServletResponse response) throws ServletException, IOException {
        ((HttpServletResponse) response).sendRedirect(
                ((HttpServletRequest) request).getContextPath() + PathConstant.INVALID_TOKEN);
    }
}
