package org.nightstudio.common.face.action.web.index;

import org.nightstudio.common.bean.AdminUser;
import org.nightstudio.common.business.AdminUserBiz;
import org.nightstudio.common.face.action.spi.NSPostAction;
import org.nightstudio.common.face.action.web.main.MainAction;
import org.nightstudio.common.util.action.ActionUtil;
import org.nightstudio.common.util.constant.PathConstant;
import org.nightstudio.common.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caoxuezhu01 on 14-10-23.
 */
@Controller
@RequestMapping(value = PathConstant.WEB_PREFIX + "/index/login.do")
public class LoginAction extends NSPostAction<AdminUser> {
    @Autowired
    protected AdminUserBiz biz;

    @Override
    protected String getSuccessPath() {
        return ActionUtil.getActionPath(MainAction.class);
    }

    @Override
    protected AdminUser renderCommandObject(AdminUser formObj, HttpServletRequest request) {
        return formObj;
    }

    @Override
    protected void handlePost(AdminUser command, HttpServletRequest request) throws Throwable {
        AdminUser adminUser = biz.login(command.getName(), command.getPasswd());
        SessionUtil.setUser(request, adminUser);
    }

    @Override
    protected void renderView(ModelAndView view, HttpServletRequest request) {

    }
}
