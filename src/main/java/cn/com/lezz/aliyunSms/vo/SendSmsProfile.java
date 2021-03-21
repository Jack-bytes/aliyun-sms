package cn.com.lezz.aliyunSms.vo;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 针对SendSms action的配置信息;
 *
 * @author Jack wang
 * @date 2020-9-18
 */
@Getter
@Setter
@Accessors(chain = true)
public class SendSmsProfile extends SmsProfile {

    /**
     * 短信中的签名信息,如【恩捷科技】中的"恩捷科技"
     */
    private String signName;

    /**
     * 短信模板ID
     */
    private String templateCode;

    public SendSmsProfile() {
    }

    public SendSmsProfile(String accessKeyId, String secret, String signName, String templateCode) {
        super(accessKeyId, secret);
        this.signName = signName;
        this.templateCode = templateCode;
    }
}
