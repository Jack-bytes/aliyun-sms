package cn.coonu.aliyunSms.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 发送结果;
 *
 * @author Jack wang
 * @date 2020-9-19
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Result {

    /**
     * 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
     * 发送失败则此值为null;
     * 请勿根据此值判断是否成功;
     */
    @SerializedName("BizId")
    private String bizId;

    /**
     * 请求状态码;
     * 返回OK代表请求成功;
     * 其他错误码详见错误码列表 https://help.aliyun.com/document_detail/101346.html;
     */
    @SerializedName("Code")
    private String code;

    /**
     * 状态码的描述;
     */
    @SerializedName("Message")
    private String message;

    /**
     * 当次请求编号;
     */
    @SerializedName("RequestId")
    private String requestId;

    /**
     * 6位验证码;
     * 只有在发送单条信息时,如果只提供手机号码,则方法会自动生成验证码并添加在结果中返回;
     */
    private String vCode;

}
