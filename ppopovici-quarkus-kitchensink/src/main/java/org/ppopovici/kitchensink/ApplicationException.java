package org.ppopovici.kitchensink;

import java.io.Serializable;

public class ApplicationException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 8635868119669415620L;

	public static int ERROR_EMAIL_EXISTS = 1;
	public static int ERROR_MEMBER_NOT_FOUND = 2;

	public static int ERROR_INTERNAL = 999;

	private int errorCode;

	public ApplicationException(String message, int errorCode) {
		super(message);

		this.setErrorCode(errorCode);
	}

	public ApplicationException(String message, int errorCode, Throwable cause) {
		super(message, cause);

		this.setErrorCode(errorCode);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
