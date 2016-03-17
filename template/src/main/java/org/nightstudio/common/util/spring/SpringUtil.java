package org.nightstudio.common.util.spring;

import org.apache.log4j.Logger;
import org.nightstudio.common.listener.spring.ApplicationContextAccess;
import org.nightstudio.common.util.exception.sys.NSException;
import org.nightstudio.common.util.exception.sys.ParameterException;
import org.nightstudio.common.util.exception.sys.SpringBeanNotFoundException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * 
 * <p>Title: SpringUtil</p>
 * <p>Description: Spring工具类</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: SI-TECH </p>
 * @author CAOXZ
 * @version 1.0
 * @time 2010-2-4 下午04:54:32
 *
 */
public class SpringUtil {
	private static final long serialVersionUID = -3386354319001931670L;
	private static Logger logger = Logger.getLogger(SpringUtil.class);
	private static ApplicationContext context = ApplicationContextAccess.getApplicationContext();
	
	/**
	 * 
	 * @Title: getBean
	 * @Description: 获取Spring中Bean对象
	 * @param
	 * @return Object
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-2-4 下午04:54:49
	 */
	public static Object getBean(String beanName) throws NSException {
		if (beanName == null) {
			logger.error("bean name is null");
			throw new ParameterException();
		}
		if (context == null) {
			logger.error("spring context is null");
			throw new SpringBeanNotFoundException(beanName);
		}
		Object bean = context.getBean(beanName);
		if (bean == null) {
			throw new SpringBeanNotFoundException(beanName);
		}
		return bean;
	}
	
	/**
	 * 
	 * @Title: getBeanOfType
	 * @Description: 跟据类型获取Spring中Bean对象
	 * @param
	 * @return Object
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @time 2010-8-25 上午10:41:18
	 */
	@SuppressWarnings("unchecked")
	public static Object getBeanOfType(Class clazz) throws NSException {
		if (clazz == null) {
			logger.error("bean clazz is null");
			throw new ParameterException();
		}
		if (context == null) {
			logger.error("spring context is null");
			throw new SpringBeanNotFoundException(clazz.getName());
		}
		Map<String, Object> map = context.getBeansOfType(clazz);
		if (map == null || map.size() == 0) {
			throw new SpringBeanNotFoundException(clazz.getName());
		}
		return map.values().toArray()[0];
	}
}
