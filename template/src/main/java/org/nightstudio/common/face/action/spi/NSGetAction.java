package org.nightstudio.common.face.action.spi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caoxuezhu01 on 14-10-8.
 */
public abstract class NSGetAction extends NSWebAction {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet(HttpServletRequest request) {
        try {
            ModelAndView result = getCommandView(request);
            renderView(result, request);
            return result;
        } catch (Throwable e) {
            return getErrorView(e);
        }
    }
}
