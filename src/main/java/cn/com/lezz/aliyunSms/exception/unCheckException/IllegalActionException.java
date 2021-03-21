package cn.com.lezz.aliyunSms.exception.unCheckException;

import cn.com.lezz.aliyunSms.common.SmsActions;
import cn.com.lezz.aliyunSms.exception.SmsRuntimeException;

/**
 * 非法的action参数异常
 *
 * @author Jack wang
 * @date 2020-9-18
 * @see SmsActions
 */
public class IllegalActionException extends SmsRuntimeException {

    public IllegalActionException() {
    }

    public IllegalActionException(String message) {
        super(message);
    }

    public IllegalActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalActionException(Throwable cause) {
        super(cause);
    }

    public IllegalActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
