package org.nightstudio.common.util.exception.sys;

import org.nightstudio.common.util.exception.errorcode.ErrorCode;


public class SystemInternalException extends NSException {
	private static final long serialVersionUID = 3511781791060172619L;
	
	public SystemInternalException () {
		super(ErrorCode.SYSTEM_INTERNAL_EXCEPTION);
	}
}
