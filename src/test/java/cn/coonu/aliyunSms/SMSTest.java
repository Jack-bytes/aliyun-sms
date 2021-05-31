package cn.coonu.aliyunSms;

import cn.coonu.aliyunSms.vo.Result;
import cn.coonu.aliyunSms.vo.SendSmsProfile;
import cn.coonu.aliyunSms.exception.checkException.OutOfSizeException;
import cn.coonu.aliyunSms.sms.SMS;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * test
 *
 * @author Jack wang
 */
public class SMSTest {

    public static SMS sms;

    @BeforeAll
    public static void before() {
        SendSmsProfile profile = new SendSmsProfile();
        profile.setSignName("镜边服务网")
                .setTemplateCode("xx")
                .setAccessKeyId("xx")
                .setSecret("xx");

        sms = new SMS(profile);

    }

    /**
     * 只通过手机号发送验证码;
     */
    @Test
    public void sendSmsByPhoneNumber() {
        Result r = sms.sendSms("xx");
        System.out.println(r);
    }

    /**
     * 发送6位数字验证码或其他信息;
     */
    @Test
    public void sendSmsWithCode() {
        Result r = sms.sendSms("13*********", "888888");
        System.out.println(r);
    }

    /**
     * 可向多个用户(不超过1000个)发送同样信息;
     */
    @Test
    public void sendSms() throws OutOfSizeException {
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("xxx****");
        phoneNumbers.add("xxxx****");

        Map<String, String> templateParams = new HashMap<>();
        templateParams.put("code", "123456");

        Result r = sms.sendSms(phoneNumbers, templateParams);
        System.out.println(r);
    }


}

