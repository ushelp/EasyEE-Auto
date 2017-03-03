package cn.easyproject.easyee.auto;

/**
 * EasyEE Auto Exception
 * @author Ray
 * @author inthinkcolor@gmail.com
 * @author easyproject.cn
 * @since 1.0.0
 */
public class EasyAutoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EasyAutoException() {
		super();
	}

	public EasyAutoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EasyAutoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EasyAutoException(String message) {
		super(message);
	}

	public EasyAutoException(Throwable cause) {
		super(cause);
	}

	
}
