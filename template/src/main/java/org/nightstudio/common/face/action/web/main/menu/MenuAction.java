/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package org.nightstudio.common.face.action.web.main.menu;

import org.nightstudio.common.face.action.spi.NSGetAction;
import org.nightstudio.common.util.constant.PathConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author xuezhu.cao Initial Created at 2012-2-18
 */
@Controller
@RequestMapping(value = PathConstant.WEB_PREFIX + "/main/menu/menu.do")
public class MenuAction extends NSGetAction {

    @Override
    protected void renderView(ModelAndView view, HttpServletRequest request) {

    }
}
