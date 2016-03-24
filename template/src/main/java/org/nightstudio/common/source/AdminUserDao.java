package org.nightstudio.common.source;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.bean.AdminUser;
import org.nightstudio.common.source.spi.AbsNSDao;
import org.springframework.stereotype.Component;

/**
 * Created by caoxuezhu01 on 14-9-14.
 */
@Component
public class AdminUserDao extends AbsNSDao {
    private static Log logger = LogFactory.getLog(AdminUserDao.class);

    public AdminUserDao() {
        super("adminUser");
    }

    public AdminUser getByName(String name) throws Throwable {
        return (AdminUser) queryObj("getByName", name);
    }

}
