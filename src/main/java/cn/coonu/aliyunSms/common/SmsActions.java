package cn.coonu.aliyunSms.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用aliyun API所执行的操作;
 *
 * @author Jack wang
 * @date 2020-9-18 13:18:53
 */
public class SmsActions {

    public final static List<String> actions;

    /**
     * 发送单条短信
     */
    public final static String SEND_SMS = "SendSms";

    /**
     * 查询发送详情
     */
    public final static String QUERY_SEND_DETAILS = "QuerySendDetails";

    /**
     * 群发消息
     */
    public final static String SEND_BATCH_SMS = "SendBatchSms";

    static {
        actions = new ArrayList<>();
        actions.add(SEND_SMS);
        actions.add(QUERY_SEND_DETAILS);
        actions.add(SEND_BATCH_SMS);
    }

}
