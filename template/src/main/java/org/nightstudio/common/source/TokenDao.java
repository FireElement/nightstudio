package org.nightstudio.common.source;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.bean.Token;
import org.nightstudio.common.source.spi.AbsRedisDao;
import org.nightstudio.common.util.redis.RedisUtil;
import org.springframework.stereotype.Component;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
@Component
public class TokenDao extends AbsRedisDao {
    private static Log logger = LogFactory.getLog(TokenDao.class);

    public Long getByToken(String token) throws Throwable {
        return this.getLong(RedisUtil.getTokenKey(token));
    }

    public Boolean create(Token token) throws Throwable {
        String key = RedisUtil.getTokenKey(token.getToken());
        if (this.setnx(key, String.valueOf(token.getUserId()))) {
            this.expire(key, token.getExpire());
            return true;
        }
        return false;
    }

    public void updateExpire(Token token) throws Throwable {
        expire(RedisUtil.getTokenKey(token.getToken()), token.getExpire());
    }

    public void delete(Token token) throws Throwable {
        delete(token.getToken());
    }
}
