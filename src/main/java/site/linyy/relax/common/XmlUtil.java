package site.linyy.relax.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/** xml数据比较复杂，本类只做业务用到的最简单的功能.
 */
public class XmlUtil {

    /**
     * 为了效率：1.返回element 2.返回具体值
     */

    // 返回document
    public static Element getDocument(String xml) {
        try {
            Document document = DocumentHelper.parseText(xml);
            return document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 返回具体值
    public static String getValue(Element e, String key) {
        return e.element(key).getStringValue();
    }

}
