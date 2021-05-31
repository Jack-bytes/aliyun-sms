package cn.coonu.aliyunSms.exception.checkException;

import cn.coonu.aliyunSms.exception.SmsException;

/**
 * 超过规定大小
 *
 * @author Jack wang
 * @date 2020-9-20
 */
public class OutOfSizeException extends SmsException {

    public OutOfSizeException() {
    }

    public OutOfSizeException(String message) {
        super(message);
    }

    public OutOfSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfSizeException(Throwable cause) {
        super(cause);
    }

    public OutOfSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
