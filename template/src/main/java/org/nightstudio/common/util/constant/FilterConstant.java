package org.nightstudio.common.util.constant;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
public interface FilterConstant {
    String[] TOKEN_FILTER_IGNORE_PATHS = {
            "regist.do",
            "login.do",
            "logout.do",
            "checkToken.do",
            "invalidToken.do"
    };
    String[] TOKEN_FILTER_PATHS = {
            PathConstant.APP_PREFIX
    };
    String[] SESSION_FILTER_IGNORE_PATHS = {
            "login.do",
            "sessionError.do"
    };
    String[] SESSION_FILTER_PATHS = {
            PathConstant.WEB_PREFIX
    };

    String[] SIG_FILTER_IGNORE_PATHS = {
    };

    String[] SIG_FILTER_PATHS = {
            PathConstant.WEB_PREFIX,
            PathConstant.APP_PREFIX
    };
}
