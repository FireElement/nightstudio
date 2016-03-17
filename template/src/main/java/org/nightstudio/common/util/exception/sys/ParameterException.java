package org.nightstudio.common.util.exception.sys;

import org.nightstudio.common.util.exception.errorcode.ErrorCode;


public class ParameterException extends NSException {
	private static final long serialVersionUID = -4898237885783517358L;
	
	public ParameterException () {
		super(ErrorCode.PARAMETER_EXCEPTION);
	}
}
