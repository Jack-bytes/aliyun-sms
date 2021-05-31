package cn.coonu.aliyunSms.exception;

/**
 * 非检查异常
 *
 * @author Jack wang
 * @date 2020-9-18
 */
public class SmsRuntimeException extends RuntimeException {

    public SmsRuntimeException() {
        super();
    }

    public SmsRuntimeException(String message) {
        super(message);
    }

    public SmsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsRuntimeException(Throwable cause) {
        super(cause);
    }

    protected SmsRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
