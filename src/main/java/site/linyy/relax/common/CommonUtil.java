package site.linyy.relax.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 普通工具类.
 */
public class CommonUtil {

    /**返回第n个匹配的索引.
     * @param str 字符串
     * @param n 数字
     */
    public static Integer getStartPosition(String str, int n) {
        Pattern p = Pattern.compile("(?:.*?\\?.*?){" + n + "}(\\?)(.*?)");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.start(1);
        }
        return null;
    }

    /**睡眠（毫秒）.
     */
    public static void sleep(long mini) {
        try {
            Thread.sleep(mini);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
