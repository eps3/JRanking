package cn.sheep3.exception;

/**
 * Created by sheep3 on 2017/10/19.
 */
public class UnKnownSystemException extends SystemException {
    public UnKnownSystemException() {
        super();
    }

    public UnKnownSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnKnownSystemException(String message) {
        super(message);
    }

    public UnKnownSystemException(Throwable cause) {
        super(cause);
    }
}
