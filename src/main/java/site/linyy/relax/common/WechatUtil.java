package site.linyy.relax.common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**微信工具 .
 */
public class WechatUtil {

    // 原始ID
    static String appId = "gh_1fec376c76c8";

    // 发送文本
    public static String getTextXml(String content, String toUserName) {
        if (StringUtils.isBlank(content)) {
            content = Integer.toString(RandomUtils.nextInt(100));
        }
        return "<xml><ToUserName><![CDATA[" + toUserName
                + "]]></ToUserName><FromUserName><![CDATA[" + appId
                + "]]></FromUserName><CreateTime>" + System.currentTimeMillis()
                + "</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["
                + content + "]]></Content></xml>";
    }

}
