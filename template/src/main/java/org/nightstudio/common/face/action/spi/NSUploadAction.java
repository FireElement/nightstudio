package org.nightstudio.common.face.action.spi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.util.constant.RequestConstant;
import org.nightstudio.common.util.lang.ObjectUtil;
import org.nightstudio.common.util.request.RequestUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by caoxuezhu01 on 15-1-16.
 */
public abstract class NSUploadAction<T> extends NSWebAction implements ServletContextAware {
    private static Log logger = LogFactory.getLog(NSPostAction.class);

    @SuppressWarnings("unchecked")
    private Class<T> tClazz = ObjectUtil.getActualTypeArguments(this.getClass(), 0);

    private ServletContext servletContext;

    protected abstract String getSuccessPath();

    protected abstract T renderCommandObject(T formObj, HttpServletRequest request) throws Throwable;

    protected abstract void handlePost(T command, CommonsMultipartFile file, HttpServletRequest request) throws Throwable;

    protected T getCommandObject() throws Exception {
        return ObjectUtil.newInstance(tClazz);
    }

    public void setServletContext(ServletContext servletContext) { //实现接口中的setServletContext方法
        this.servletContext = servletContext;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet(HttpServletRequest request) {
        try {
            ModelAndView modelAndView = getCommandView(request);
            T formObj = getCommandObject();
            formObj = renderCommandObject(formObj, request);
            renderView(modelAndView, request);
            RequestUtil.setCommand(modelAndView, formObj);
            return modelAndView;
        } catch (Throwable e) {
            logger.warn("", e);
            return getErrorView(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doPost(@RequestParam(RequestConstant.PARAM_KEY_FILE) CommonsMultipartFile file,
                               @Valid @ModelAttribute(RequestConstant.PARAM_KEY_COMMAND) T command,
                               BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = getCommandView(request);
            RequestUtil.setCommand(modelAndView, command);
            return modelAndView;
        }
        try {
            handlePost(command, file, request);
        } catch (Throwable e) {
            logger.warn("", e);
            String msg = getErrorMsg(e);
            FieldError error = new FieldError(RequestConstant.PARAM_KEY_COMMAND, "", msg);
            result.addError(error);
            ModelAndView modelAndView =  getCommandView(request);
            RequestUtil.setCommand(modelAndView, command);
            try {
                renderView(modelAndView, request);
            } catch (Throwable e1) {
                logger.warn("", e);
            }
            return modelAndView;
        }
        return new ModelAndView(getRedirectViewName(getSuccessPath()));
    }


}
