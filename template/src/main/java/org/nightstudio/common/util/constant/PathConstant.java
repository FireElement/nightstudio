package org.nightstudio.common.util.constant;

/**
 * Created by caoxuezhu01 on 14-10-24.
 */
public interface PathConstant {
    String APP_PREFIX = "/app";
    String WEB_PREFIX = "/web";

    String INVALID_TOKEN = APP_PREFIX + "/token/invalidToken.do";
    String SESSION_ERROR = WEB_PREFIX + "/sessionError.do";
}
