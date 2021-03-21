package cn.com.lezz.aliyunSms.exception;

/**
 * 检查异常
 *
 * @author Jack wang
 * @date 2020-9-18
 */
public class SmsException extends Exception {

    public SmsException() {
        super();
    }

    public SmsException(String message) {
        super(message);
    }

    public SmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsException(Throwable cause) {
        super(cause);
    }

    protected SmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
