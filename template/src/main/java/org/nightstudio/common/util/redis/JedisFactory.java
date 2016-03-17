package org.nightstudio.common.util.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.util.constant.PropertiesConstant;
import org.nightstudio.common.util.exception.sys.ParameterException;
import org.nightstudio.common.util.exception.sys.SystemInternalException;
import org.nightstudio.common.util.properties.PropertiesUtil;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis工厂方法
 */
public class JedisFactory {
    private static ShardedJedisPool pool = null;
    private static Log logger = LogFactory.getLog(JedisFactory.class);

    static {
        JedisPoolConfig config =new JedisPoolConfig();
        config.setMaxTotal(PropertiesUtil.getIntProperty(PropertiesConstant.JEDIS_MAX_ACTIVE));
        config.setMaxIdle(PropertiesUtil.getIntProperty(PropertiesConstant.JEDIS_MAX_IDLE));
        config.setMaxWaitMillis(PropertiesUtil.getIntProperty(PropertiesConstant.JEDIS_MAX_WAIT));
        config.setTestOnBorrow(PropertiesUtil.getBoolProperty(PropertiesConstant.JEDIS_TEST_ON_BORROW));

        List<JedisShardInfo> sharedInfos = new ArrayList<JedisShardInfo>(1);
        JedisShardInfo shardInfo = new JedisShardInfo(
                PropertiesUtil.getProperty(PropertiesConstant.REDIS_SERVER_1_IP),
                PropertiesUtil.getProperty(PropertiesConstant.REDIS_SERVER_1_PORT));
        sharedInfos.add(shardInfo);

        pool = new ShardedJedisPool(config, sharedInfos);
    }

    public static ShardedJedis getJedis() throws Throwable {
        ShardedJedis jedis;
        try {
            jedis = pool.getResource();
        } catch (Throwable e) {
            logger.warn("", e);
            throw new SystemInternalException();
        }
        return jedis;
    }

    public static void returnJedis(ShardedJedis jedis) throws Throwable {
        if (jedis == null) {
            throw new ParameterException();
        }
        if (pool == null) {
            throw new SystemInternalException();
        }
        pool.returnResourceObject(jedis);
    }

}
