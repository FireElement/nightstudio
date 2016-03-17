package org.nightstudio.common.business;

import org.apache.commons.lang.StringUtils;
import org.nightstudio.common.bean.Token;
import org.nightstudio.common.source.TokenDao;
import org.nightstudio.common.util.constant.TokenConstant;
import org.nightstudio.common.util.exception.errorcode.ErrorCode;
import org.nightstudio.common.util.exception.sys.NSException;
import org.nightstudio.common.util.exception.sys.ParameterException;
import org.nightstudio.common.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by caoxuezhu01 on 14-9-14.
 */
@Component
public class TokenBiz {
    Random random = new Random();
    @Autowired
    protected TokenDao dao;

    public Token get(String token) throws Throwable {
        Long userId = dao.getByToken(token);
        if (userId == null) {
            throw new NSException(ErrorCode.INVALID_TOKEN);
        }
        Token result = new Token();
        result.setToken(token);
        result.setUserId(userId);
        return result;
    }

    public Token check(String token) throws Throwable {
        Token result = get(token);
        result.setToken(token);
        result.setExpire(TokenConstant.ALIVE_TIME);
        dao.updateExpire(result);
        return result;
    }

    public Token create(Token token) throws Throwable {
        if (token == null ||
            token.getUserId() == null) {
            throw new ParameterException();
        }
        long time = System.currentTimeMillis();
        String t = StringUtil.getStrOfLength(
                '0',
                String.valueOf(time % TokenConstant.TIME_PART_MOD),
                TokenConstant.TIME_PART_LENGTH);
        String r = StringUtil.getStrOfLength(
                '0',
                String.valueOf(random.nextInt() % TokenConstant.RANDOM_PART_MOD),
                TokenConstant.RANDOM_PART_LENGTH);
        t = t + r;
        token.setToken(t);
        token.setExpire(TokenConstant.ALIVE_TIME);
        if (!dao.create(token)) {
            throw new NSException(ErrorCode.CREATE_TOKEN_FAIL);
        }
        return token;
    }

    public void delete(Token token) throws Throwable {
        if (token == null ||
            token.getUserId() == null ||
            StringUtils.isEmpty(token.getToken())) {
            throw new ParameterException();
        }
        Token t = get(token.getToken());
        if (!t.getUserId().equals(token.getUserId())) {
            throw new NSException(ErrorCode.INVALID_TOKEN);
        }
        dao.delete(t);
    }
}
