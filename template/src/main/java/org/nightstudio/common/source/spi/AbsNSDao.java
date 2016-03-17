package org.nightstudio.common.source.spi;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.log4j.Logger;
import org.nightstudio.common.bean.Pagination;
import org.nightstudio.common.util.constant.DaoConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.util.HashMap;
import java.util.Map;

public abstract class AbsNSDao<T> {
    private static Logger logger = Logger.getLogger(AbsNSDao.class);
    @Autowired
    protected SqlMapClientTemplate sqlMapClientTemplate;
    private SqlMapClient sqlMapClient = null;
    private Object lockObj = new Object();

    protected SqlMapClient getSqlMapClient() {
        if (sqlMapClient == null) {
            synchronized (lockObj) {
                if (sqlMapClient == null) {
                    sqlMapClient = this.sqlMapClientTemplate.getSqlMapClient();
                }
            }
        }
        return sqlMapClient;
    }

    protected Map<String, Object> getPageMap(Pagination pagination) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        return getPageMap(pagination, result);
    }

    protected Map<String, Object> getPageMap(Pagination pagination, Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        if (pagination == null) {
            return paramMap;
        }
        paramMap.put(DaoConstant.PARAM_KEY_OFFSET, pagination.getFirstResult());
        paramMap.put(DaoConstant.PARAM_KEY_SIZE, pagination.getPageSize());
        return paramMap;
    }

}
