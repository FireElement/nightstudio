package org.nightstudio.common.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.nightstudio.common.source.spi.AbsRedisDao;
import org.nightstudio.common.util.exception.sys.SystemInternalException;
import org.nightstudio.common.util.redis.JedisFactory;
import org.springframework.stereotype.Component;

/**
 * Redis方法切面，当redis方法抛出异常时，释放连接；正常访问后，返回连接到redis连接池中
 */
@Aspect
@Component
public class AopNSRedisDao {
    private final static String el1 = "execution(* org.nightstudio.common.source.TokenDao.*(..))";
    private final static String el2 = "execution(* com.ns.template.source.redis..*(..))";
    private static Log logger = LogFactory.getLog(AopNSRedisDao.class);

    /**
     * TokenDao中所有方法切面
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(el1)
    public Object process1(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint);
    }

    /**
     * 业务方法中用到redis的DAO
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(el2)
    public Object process2(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint);
    }

    private Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        AbsRedisDao redisDao = null;
        try {
            redisDao = (AbsRedisDao) joinPoint.getTarget();
            redisDao.setJedis(JedisFactory.getJedis());
            return joinPoint.proceed();
        } catch (Throwable e) {
            logger.error("", e);
            throw new SystemInternalException();
        } finally {
            if (redisDao != null) {
                JedisFactory.returnJedis(redisDao.getJedis());
                redisDao.setJedis(null);
            }
        }
    }
}
