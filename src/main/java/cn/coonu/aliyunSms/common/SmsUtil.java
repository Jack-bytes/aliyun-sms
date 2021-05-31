package cn.coonu.aliyunSms.common;

/**
 * 工具类
 *
 * @author Jack wang
 * @date 2020-9-18
 */
public class SmsUtil {

    public static boolean isEmpty(String str) {
        // ||短路
        return str == null || "".equals(str.trim());
    }

    /**
     * 任何一个为Empty,都为true
     *
     * @param strs 多个字符串
     * @return 任何一个为Empty, 都返回true, 否则false;
     */
    public static boolean isEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || "".equals(str.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成6位随机验证码
     *
     * @return 6位数字验证码
     */
    public static String generateCode() {
        int code = (int) (Math.random() * 1000000);
        return Integer.toString(code);
    }

}
