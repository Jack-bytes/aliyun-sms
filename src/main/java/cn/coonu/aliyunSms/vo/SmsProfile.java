package cn.coonu.aliyunSms.vo;

import cn.coonu.aliyunSms.common.SmsActions;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * SMS相关公共配置信息;
 *
 * @author Jack wang
 * @date 2020-09-18
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class SmsProfile {

	/*--------------------------------------------
	|    I N S T A N C E   V A R I A B L E S    |
	============================================*/

    /**
     * area code;
     */
    private String regionId = "cn-hangzhou";

    /**
     * domain where to send request;
     */
    private String domain = "dysmsapi.aliyuncs.com";

    /**
     * SDK version;
     */
    private String version = "2017-05-25";

    /**
     * The operation what you want to execute;
     */
    private String action = SmsActions.SEND_SMS;

    /**
     * Identification of the user account(or the sub_account) in aliyun.com;
     */
    private String accessKeyId;

    /**
     * The secret what generate by aliyun and to encrypt url, you can find it in sms console of aliyun.com;
     */
    private String secret;

    public SmsProfile() {
    }

    public SmsProfile(String accessKeyId, String secret) {
        this.accessKeyId = accessKeyId;
        this.secret = secret;
    }
}
