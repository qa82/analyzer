package org.qa82.analyzer.core.exceptions;

public class InformationNeedNotResolvableException extends Throwable {

	private static final long serialVersionUID = 6230878960955119432L;

	public InformationNeedNotResolvableException() {
		super("Information need could not be resolved.");
	}

	public InformationNeedNotResolvableException(String message) {
		super(message);
	}

	public InformationNeedNotResolvableException(Throwable cause) {
		super(cause);
	}

	public InformationNeedNotResolvableException(String message, Throwable cause) {
		super(message, cause);
	}

}
