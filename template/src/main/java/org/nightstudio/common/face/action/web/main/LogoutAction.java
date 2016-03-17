/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package org.nightstudio.common.face.action.web.main;

import org.nightstudio.common.face.action.spi.NSGetAction;
import org.nightstudio.common.util.constant.PathConstant;
import org.nightstudio.common.util.session.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author xuezhu.cao Initial Created at 2012-2-20
 */
@Controller
@RequestMapping(value = PathConstant.WEB_PREFIX + "/main/logout.do")
public class LogoutAction extends NSGetAction {

    @Override

    protected void renderView(ModelAndView view, HttpServletRequest request) {
        SessionUtil.removeUser(request);
    }
}
