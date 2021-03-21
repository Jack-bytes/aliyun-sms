package cn.com.lezz.aliyunSms.sms;

import cn.com.lezz.aliyunSms.common.SmsActions;
import cn.com.lezz.aliyunSms.common.SmsUtil;
import cn.com.lezz.aliyunSms.exception.unCheckException.IllegalActionException;
import cn.com.lezz.aliyunSms.vo.Result;
import cn.com.lezz.aliyunSms.vo.SendSmsProfile;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import cn.com.lezz.aliyunSms.exception.checkException.OutOfSizeException;
import cn.com.lezz.aliyunSms.exception.unCheckException.IllegalProfileException;
import cn.com.lezz.aliyunSms.vo.SmsProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 发送消息
 *
 * @author Jack wang
 * @date 2020-09-18
 */
public class SMS {

    /*--------------------------------------------
	|             C O N S T A N T S             |
	============================================*/

    private final static transient Logger log = LoggerFactory.getLogger(SMS.class);

    private final static Gson gson = new Gson();

    /*--------------------------------------------
	|    I N S T A N C E   V A R I A B L E S    |
	============================================*/

    private SmsProfile profile;

    /*--------------------------------------------
	|         C O N S T R U C T O R S           |
	============================================*/

//    public SMS() {   //不提供无参构造, 防止profile为null时被使用;
//    }

    public SMS(SmsProfile profile) {
        verifyProfile(profile);
        this.profile = profile;
    }

    /*--------------------------------------------
	|  A C C E S S O R S / M O D I F I E R S    |
	============================================*/

//    public SmsProfile getProfile() {
//        return profile;
//    }

    public void setProfile(SmsProfile profile) {
        verifyProfile(profile);
        this.profile = profile;
    }

    /*--------------------------------------------
	|               M E T H O D S               |
	============================================*/

    /**
     * 给指定手机号码发送验证码短信,验证码自动生成;
     * 注: 使用此方法,需要短信模板中的变量值最多有一个且为"code";
     *
     * @param phoneNumber 手机号码
     * @return 发送结果, 附带随机生成的验证码;
     */
    public Result sendSms(String phoneNumber) {
        String code = SmsUtil.generateCode();
        return sendSms(phoneNumber, code).setVCode(code);
    }

    /**
     * 给指定手机号码发送一条验证码数据,
     * 注: 使用此方法,需要短信模板中的变量值最多有一个且为"code";
     *
     * @param phoneNumber 手机号码
     * @param code        验证码数据
     * @return 返回null或者其中code不是OK则发送失败;
     */
    public Result sendSms(String phoneNumber, String code) {
        List<String> phoneNumbers = new ArrayList<>(1);
        phoneNumbers.add(phoneNumber);
        Map<String, String> templateParam = new HashMap<>(1);
        templateParam.put("code", code);

        Result result = null;
        try {
            result = sendSms(phoneNumbers, templateParam);
        } catch (OutOfSizeException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向多个号码发送相同的短信,最多支持1000个号码;
     *
     * @param phoneNumbers  多个手机号码
     * @param templateParam 模板数据如: {"code": "888888"},  code为短信模板中的变量,模板中变量可以有多个;
     * @return 返回null或者其中code不是OK则发送失败;
     */
    public Result sendSms(List<String> phoneNumbers, Map<String, String> templateParam) throws OutOfSizeException {
        if (!(this.profile instanceof SendSmsProfile)) {
            throw new IllegalProfileException("配置信息错误!");
        }
        if (phoneNumbers == null || phoneNumbers.size() > 1000 || phoneNumbers.size() == 0) {
            throw new OutOfSizeException("手机号码数量为0或者超过了1000个!");
        }
        SendSmsProfile realProfile = (SendSmsProfile) this.profile;
        DefaultProfile profile = DefaultProfile.getProfile(realProfile.getRegionId(), realProfile.getAccessKeyId(), realProfile.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(realProfile.getDomain());
        request.setSysVersion(realProfile.getVersion());
        request.setSysAction(realProfile.getAction());
        String numbers = phoneNumbers.toString();
        String formattedNumbers = numbers.substring(1, numbers.length() - 1).replaceAll(" ", "");
        request.putQueryParameter("PhoneNumbers", formattedNumbers);
        request.putQueryParameter("SignName", realProfile.getSignName());
        request.putQueryParameter("TemplateCode", realProfile.getTemplateCode());
        String gsonTemplateParam = gson.toJson(templateParam);
        request.putQueryParameter("TemplateParam", gsonTemplateParam);

        Result result = null;
        try {

            CommonResponse response = client.getCommonResponse(request);
            result = gson.fromJson(response.getData(), Result.class);

            if (log.isDebugEnabled()) {
                if ("OK".equals(result.getCode())) {
                    log.debug("短信发送成功, 手机号码: {}, 发送数据: {}!", formattedNumbers, gsonTemplateParam);
                } else {
                    log.debug("短信发送失败, 手机号码: {}, 错误码: {}, 错误码详情请参照 https://help.aliyun.com/document_detail/101346.html", formattedNumbers, result.getCode());
                }
            }

        } catch (ServerException e) {
            log.error("向手机号码{}发送短信失败,阿里云短信平台服务器故障,请稍后重试!", formattedNumbers);
        } catch (ClientException e) {
            log.error("向手机号码{}发送短信失败,请检查url是否配置正确!", formattedNumbers);
        }
        return result;

    }

    /**
     * 校验profile
     *
     * @param profile 配置
     */
    private static void verifyProfile(SmsProfile profile) {
        if (profile == null) {
            throw new IllegalProfileException("The profile can not be null!");
        }

        if (SmsUtil.isEmpty(profile.getAccessKeyId(), profile.getSecret())) {
            throw new IllegalProfileException("The one of the accessKeyId and secret is empty, or both is!");
        }

        String action = profile.getAction();
        if (!SmsActions.actions.contains(action)) {
            throw new IllegalActionException("The action named \"" + action + "\" is Unsupported, please replace with others in SmsActions.class!");
        }
    }

}
