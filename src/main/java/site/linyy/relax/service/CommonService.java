package site.linyy.relax.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/** 为thymeleaf工具类.
 */
@Service
public class CommonService {

    // 将文件全路径转换为web的source路径
    public String pathToSource(String path) {

        if (StringUtils.isNotBlank(path)) {
            return "/source/" + path.replaceAll("\\\\", "/").split("/", 2)[1];
        }
        return null;
    }

    // 判断str是否以xxx结尾
    public boolean endsWith(String str, String[] oneOfSuffix) {

        if (StringUtils.isNotBlank(str) && oneOfSuffix != null
                && oneOfSuffix.length > 0) {
            str = str.toLowerCase();
            for (int i = 0; i < oneOfSuffix.length; i++) {
                if (str.endsWith(oneOfSuffix[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    // 转义
    public String escape(String str) {

        if (StringUtils.isNotBlank(str)) {
            return str.replaceAll("\\\\", "%5C").replaceAll(" ", "%20");
        }
        return str;
    }

    // \替换为\\,ajax需要
    public String replace(String str) {

        if (StringUtils.isNotBlank(str)) {
            return str.replaceAll("\\\\", "\\\\\\\\");
        }
        return str;
    }

}
