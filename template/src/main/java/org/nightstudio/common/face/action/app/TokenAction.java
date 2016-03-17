package org.nightstudio.common.face.action.app;

import org.nightstudio.common.business.TokenBiz;
import org.nightstudio.common.face.action.spi.NSJsonAction;
import org.nightstudio.common.util.constant.PathConstant;
import org.nightstudio.common.util.exception.errorcode.ErrorCode;
import org.nightstudio.common.util.exception.sys.NSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by caoxuezhu01 on 14-9-13.
 */
@Controller
@RequestMapping(PathConstant.APP_PREFIX + "/token")
public class TokenAction extends NSJsonAction {
    @Autowired
    protected TokenBiz biz;

    @RequestMapping("/invalidToken.do")
    @ResponseBody
    public Object invalidToken() throws Throwable {
        throw new NSException(ErrorCode.INVALID_TOKEN);
    }

    @RequestMapping("/checkToken.do")
    @ResponseBody
    public Object check(@RequestParam String token) throws Throwable {
        return biz.check(token);
    }

}
