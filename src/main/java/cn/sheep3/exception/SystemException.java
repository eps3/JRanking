package cn.sheep3.exception;

/**
 * Created by sheep3 on 2017/10/19.
 */
public class SystemException extends Exception {
    public SystemException() {
        super();
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }
}
