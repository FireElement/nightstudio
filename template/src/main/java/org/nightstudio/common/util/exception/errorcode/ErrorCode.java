package org.nightstudio.common.util.exception.errorcode;

public enum ErrorCode {
	DATABASE_OPERATION_EXCEPTION("ERROR_CODE_001"),
	PARAMETER_EXCEPTION("ERROR_CODE_002"),
	SPRING_BEAN_NOT_FOUND_EXCEPTION("ERROR_CODE_003"),
	SYSTEM_INTERNAL_EXCEPTION("ERROR_CODE_004"),
    INVALID_TOKEN("ERROR_CODE_005"),
    USER_NOT_EXIST("ERROR_CODE_006"),
    INVALID_PASSWD("ERROR_CODE_007"),
	HTTP_EXCEPTION("ERROR_CODE_008"),
	CREATE_TOKEN_FAIL("ERROR_CODE_009");
	
	private String value;
	
	ErrorCode (String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
