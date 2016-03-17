package org.nightstudio.common.source.spi;

import org.apache.commons.lang.StringUtils;
import org.nightstudio.common.util.exception.sys.SystemInternalException;
import redis.clients.jedis.ShardedJedis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuezhucao on 15/7/19.
 */
public abstract class AbsRedisDao {
    private ThreadLocal<ShardedJedis> sharedJedis = new ThreadLocal<ShardedJedis>();

    public ShardedJedis getJedis() throws Throwable {
        ShardedJedis result = sharedJedis.get();
        if (result == null) {
            throw new SystemInternalException();
        }
        return result;
    }

    public void setJedis(ShardedJedis jedis) {
        sharedJedis.set(jedis);
    }

    protected String get(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return getJedis().get(key);
    }

    protected Long getLong(String key) throws Throwable {
        String value = get(key);
        if (value != null) {
            return Long.valueOf(value);
        }
        return null;
    }

    protected void set(String key, String value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(value)) {
            return;
        }
        getJedis().set(key, value);
    }

    protected void hset(String key, String field, String value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(field)) {
            return;
        }
        getJedis().hset(key, field, value);
    }

    protected Set<String> hkeys(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return new HashSet<String>(0);
        }
        return getJedis().hkeys(key);
    }

    protected Map<String, String> hgetAll(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return new HashMap<String, String>(0);
        }
        return getJedis().hgetAll(key);
    }

    protected boolean setnx(String key, String value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(value)) {
            return false;
        }
        if (getJedis().setnx(key, value) == 1) {
            return true;
        }
        return false;
    }

    protected Long incr(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return getJedis().incr(key);
    }

    protected Long decr(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return getJedis().decr(key);
    }

    protected void delete(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        getJedis().del(key);
    }

    protected void hDelete(String key, String field) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(field)) {
            return;
        }
        getJedis().hdel(key, field);
    }

    protected Long hincr(String key, String field, long value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(field)
                || value < 0) {
            return null;
        }
        return getJedis().hincrBy(key, field, value);
    }

    protected Long hdecr(String key, String field, long value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(field)
                || value < 0) {
            return null;
        }
        return getJedis().hincrBy(key, field, -value);
    }

    protected void expire(String key, int expire) throws Throwable {
        if (StringUtils.isEmpty(key)
                || expire < 0) {
            return;
        }
        getJedis().expire(key, expire);
    }

    protected void defer(String key, int expire) throws Throwable {
        long ttl = getJedis().ttl(key);
        if (ttl > -2 && expire > ttl) {
            getJedis().expire(key, expire);
        }
    }

    protected Long zremRange(String key, double start, double end) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return getJedis().zremrangeByScore(key, start, end);
    }

    protected Set<String> zrange(String key, long start, long end) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return getJedis().zrange(key, start, end);
    }

    protected Long zadd(String key, String member, long score) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return getJedis().zadd(key, score, member);
    }
}
