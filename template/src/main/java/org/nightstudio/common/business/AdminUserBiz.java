package org.nightstudio.common.business;

import org.nightstudio.common.bean.AdminUser;
import org.nightstudio.common.source.AdminUserDao;
import org.nightstudio.common.util.exception.errorcode.ErrorCode;
import org.nightstudio.common.util.exception.sys.NSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by caoxuezhu01 on 14-9-14.
 */
@Component
public class AdminUserBiz {
    @Autowired
    protected AdminUserDao dao;

    public AdminUser login(String name, String passwd) throws Throwable {
        AdminUser adminUser = dao.getByName(name);
        if (adminUser == null) {
            throw new NSException(ErrorCode.USER_NOT_EXIST);
        }
        if (!adminUser.getPasswd().equals(passwd)) {
            throw new NSException(ErrorCode.INVALID_PASSWD);
        }
        return adminUser;
    }

}
