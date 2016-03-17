package org.nightstudio.common.face.action.spi;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.bean.Pagination;
import org.nightstudio.common.util.action.ActionUtil;
import org.nightstudio.common.util.constant.ApplicationConstant;
import org.nightstudio.common.util.constant.RequestConstant;
import org.nightstudio.common.util.error.ErrorUtil;
import org.nightstudio.common.util.exception.sys.NSException;
import org.nightstudio.common.util.exception.sys.SystemInternalException;
import org.nightstudio.common.util.pagination.RowCountFetcher;
import org.nightstudio.common.util.session.SessionUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by caoxuezhu01 on 14-9-28.
 */
public abstract class NSWebAction {
    private static Log logger = LogFactory.getLog(NSWebAction.class);

    protected abstract void renderView(ModelAndView view, HttpServletRequest request) throws Throwable;

    protected String getCommandPath() {
        return ActionUtil.getViewPath(this.getClass());
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    protected ModelAndView getCommandView(HttpServletRequest request) {
        String view = getCommandPath();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(view);
        transformParameters(request, modelAndView);
        return modelAndView;
    }

    protected String getRedirectViewName(Class<?> clazz) {
        return getRedirectViewName(ActionUtil.getActionPath(clazz));
    }

    protected String getRedirectViewName(String path) {
        return "redirect:" + path;
    }

    protected void transformParameters(HttpServletRequest request, ModelAndView modelAndView) {
        Set<String> keys = request.getParameterMap().keySet();
        Map<String, Object> map = new HashMap<String, Object>(keys.size());
        for (String key : keys) {
            if (request.getParameterValues(key) != null) {
                if (request.getParameterValues(key).length == 1) {
                    map.put(key, request.getParameter(key));
                } else {
                    map.put(key, request.getParameterValues(key));
                }
            }
        }
        modelAndView.addAllObjects(map);
    }

    protected ModelAndView getErrorView(Throwable e) {
        if (e instanceof NSException) {
            return ErrorUtil.getErrorView((NSException) e);
        } else {
            logger.warn("", e);
            NSException exception = new SystemInternalException();
            return ErrorUtil.getErrorView(exception);
        }
    }

    protected String getErrorMsg(Throwable e) {
        if (e instanceof NSException) {
            return ErrorUtil.getErrorMessage((NSException) e);
        } else {
            logger.warn("", e);
            NSException exception = new SystemInternalException();
            return ErrorUtil.getErrorMessage(exception);
        }
    }

    protected Pagination getPagination(HttpServletRequest request, RowCountFetcher rowCountFetcher) {
        return getPagination(request, ApplicationConstant.PAGE_SIZE, rowCountFetcher);
    }

    protected Pagination getPagination(HttpServletRequest request, int pageSize, RowCountFetcher rowCountFetcher) {
        String strCurrPage = request.getParameter(RequestConstant.PARAM_KEY_CURRENT_PAGE);
        Pagination pagination;
        if (StringUtils.isNotEmpty(strCurrPage)
                && (pagination = SessionUtil.getPagination(request)) != null
                && pagination.isInit()) {
            long currPage = Long.parseLong(strCurrPage);
            pagination.setCurrPage(currPage);
        } else {
            pagination = new Pagination(pageSize);
            try {
                pagination.setRowCount(rowCountFetcher.getRowCount());
            } catch (Throwable e) {
                logger.warn("", e);
            }
        }
        SessionUtil.setPagination(request, pagination);
        return pagination;
    }
}
