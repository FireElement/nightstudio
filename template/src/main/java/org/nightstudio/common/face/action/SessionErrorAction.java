package org.nightstudio.common.face.action;

import org.nightstudio.common.face.action.spi.NSGetAction;
import org.nightstudio.common.util.constant.PathConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = PathConstant.WEB_PREFIX + "/sessionError.do")
public class SessionErrorAction extends NSGetAction {

    @Override
    protected void renderView(ModelAndView view, HttpServletRequest request) {

    }
}
