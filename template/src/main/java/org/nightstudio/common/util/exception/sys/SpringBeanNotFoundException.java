package org.nightstudio.common.util.exception.sys;

import org.nightstudio.common.util.exception.errorcode.ErrorCode;


public class SpringBeanNotFoundException extends NSException {
	private static final long serialVersionUID = 8272790648827568428L;
	
	public SpringBeanNotFoundException (String beanName) {
		super(ErrorCode.SPRING_BEAN_NOT_FOUND_EXCEPTION, beanName);
	}
}
