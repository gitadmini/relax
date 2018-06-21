package site.linyy.relax.common;

import java.util.ArrayList;
import java.util.List;

/** 集合工具.
 */
public class ListUtil {

    /**倒序.
     */
    public static <T> List<T> reverse(List<T> list) {
        if (list != null && list.size() > 0) {
            List<T> resultList = new ArrayList<T>();
            for (int i = list.size() - 1; i > -1; i--) {
                resultList.add(list.get(i));
            }
            return resultList;
        }
        return list;
    }

    /**获得指定位置的集合.
     */
    public static <T> List<T> getLimit(List<T> list, int limit, int offset) {

        if (list != null && list.size() > 0) {
            List<T> resultList = new ArrayList<T>();
            int max = limit + offset;
            if (max > list.size()) {
                max = list.size();
            }
            for (int i = offset; i < max; i++) {
                resultList.add(list.get(i));
            }
            return resultList;
        }
        return null;
    }

}
