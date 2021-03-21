package cn.com.lezz.aliyunSms.exception.unCheckException;

import cn.com.lezz.aliyunSms.exception.SmsRuntimeException;

/**
 * 配置异常
 *
 * @author Jack wang
 * @date 2020-9-20
 */
public class IllegalProfileException extends SmsRuntimeException {

    public IllegalProfileException() {
    }

    public IllegalProfileException(String message) {
        super(message);
    }

    public IllegalProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalProfileException(Throwable cause) {
        super(cause);
    }

    public IllegalProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
