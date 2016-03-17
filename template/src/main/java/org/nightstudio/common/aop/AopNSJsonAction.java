package org.nightstudio.common.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.nightstudio.common.util.exception.sys.NSException;
import org.nightstudio.common.util.exception.sys.SystemInternalException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxz on 6/19/14.
 */
@Aspect
@Component
public class AopNSJsonAction {
    private static Log logger = LogFactory.getLog(AopNSJsonAction.class);

//    private final static String el = "@annotation(org.springframework.web.bind.annotation.RequestMapping)";
    private final static String el = "execution(* com.ns.template.face.app..*(..))";
    private final static String el1 = "execution(* org.nightstudio.common.face.action.app..*(..))";

    @Around(el)
    public Object around(ProceedingJoinPoint joinPoint) {
        return handle(joinPoint);
    }

    @Around(el1)
    public Object around1(ProceedingJoinPoint joinPoint) {
        return handle(joinPoint);
    }

    private Object handle(ProceedingJoinPoint joinPoint) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        String error = "0";
        Object data;
        try {
            data = joinPoint.proceed();
        } catch (Throwable e) {
            logger.warn("", e);
            NSException e1;
            if (e instanceof NSException) {
                e1 = (NSException) e;
            } else {
                e1 = new SystemInternalException();
            }
            error = e1.getErrorCode().getValue();
            Map<String, String> map = new HashMap<String, String>(1);
            map.put("msg", e1.getMessage());
            data = map;
        }
        result.put("error", error);
        result.put("data", data);
        return result;
    }
}
