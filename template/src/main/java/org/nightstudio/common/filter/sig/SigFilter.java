package org.nightstudio.common.filter.sig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.filter.spi.AbsFilter;
import org.nightstudio.common.util.constant.FilterConstant;
import org.nightstudio.common.util.constant.PathConstant;
import org.nightstudio.common.util.constant.SigConstant;
import org.nightstudio.common.util.sig.SigUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SigFilter extends AbsFilter {
    private static Log logger = LogFactory.getLog(SigFilter.class);

    @Override
    protected String[] getIgnorePaths() {
        return FilterConstant.SIG_FILTER_IGNORE_PATHS;
    }

    @Override
    protected String[] getFilterPaths() {
        return FilterConstant.SIG_FILTER_PATHS;
    }

    protected boolean checkRequest(ServletRequest request) {
        Map<String, String> params = getParam(request);
        if (!params.containsKey(SigConstant.PARAM_TIME_STAMP)) {
            return false;
        }
        if (!params.containsKey(SigConstant.PARAM_SIG)) {
            return false;
        }

        try {
            long clientTime = Long.parseLong(params.get(SigConstant.PARAM_TIME_STAMP));
            if (Math.abs((clientTime - System.currentTimeMillis())) > SigConstant.EXPIRE_TIME) {
                return false;
            }
        } catch (Throwable e) {
            logger.warn("", e);
            return false;
        }

        String sig = params.remove(SigConstant.PARAM_SIG);
        try {
            if (!SigUtil.isValidSig(sig, new ArrayList<String>(params.values()))) {
                return false;
            }
        } catch (Throwable e) {
            logger.warn("", e);
            return false;
        }
        return true;
    }

    protected void handleBadRequest(ServletRequest request,
                                    ServletResponse response) throws ServletException, IOException {
        ((HttpServletResponse) response).sendRedirect(
                ((HttpServletRequest) request).getContextPath() + PathConstant.SESSION_ERROR);
    }

    /**
     * 获取GET请求中URL中的参数，和POST请求request body中的数据
     * @param request
     * @return
     */
    private Map<String, String> getParam(ServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        // 获取请求参数
        Map parameterMap = request.getParameterMap();
        if (parameterMap != null && !parameterMap.isEmpty()) {
            Iterator iterator = parameterMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                String value = request.getParameter(key);
                params.put(key, value);
            }
        }

        return params;
    }
}
