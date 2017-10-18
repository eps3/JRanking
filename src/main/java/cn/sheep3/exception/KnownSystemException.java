package cn.sheep3.exception;

/**
 * Created by sheep3 on 2017/10/19.
 */
public class KnownSystemException extends SystemException {
    public KnownSystemException() {
        super();
    }

    public KnownSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public KnownSystemException(String message) {
        super(message);
    }

    public KnownSystemException(Throwable cause) {
        super(cause);
    }
}
