package org.nightstudio.common.util.exception.sys;

import org.nightstudio.common.util.error.ErrorUtil;
import org.nightstudio.common.util.exception.errorcode.ErrorCode;

public class NSException extends Exception {
	private static final long serialVersionUID = -8929714273087267405L;
	private ErrorCode errorCode;
	private Object[] args;
	
	public NSException() {
		super();
	}
	
	public NSException(ErrorCode errorCode) {
		this(errorCode, new Object[0]);
	}

	public NSException(ErrorCode errorCode, Object arg) {
		this(errorCode, new Object[]{arg});
	}
	
	public NSException(ErrorCode errorCode, Object[] args) {
		super();
		this.errorCode = errorCode;
		if (args == null) {
			this.args = new Object[0];
		} else {
			this.args = args;
		}
	}

	public ErrorCode getErrorCode() {
		return this.errorCode;
	}
	
	public Object[] getArgs() {
		return this.args;
	}
	
	@Override
	public String getMessage() {
		return this.toString();
	}

	@Override
	public String toString() {
		return ErrorUtil.getErrorMessage(this);
	}
}
