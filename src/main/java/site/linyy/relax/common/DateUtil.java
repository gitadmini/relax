package site.linyy.relax.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 日期、时间工具.
 */
public class DateUtil {

    /**将整数形式的转成Date.
     * @param intDate 20170101
     */
    public static Date convert2Date(int intDate) {
        final SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sdf_yyyyMMdd.parse(String.valueOf(intDate));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**返回当前时间.
     */
    public static String getCurrentTimeStr() {
        final SimpleDateFormat sdf_yMdHms = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String back = sdf_yMdHms.format(date);
        return back;
    }

    /**返回当前日期对应的横线型式.
     */
    public static String getCurrentDateHengxian() {
        final SimpleDateFormat sdf_yyyy_MM_dd = new SimpleDateFormat(
            "yyyy-MM-dd");
        Date date = new Date();
        String dateStr = sdf_yyyy_MM_dd.format(date);
        return dateStr;
    }

    public static String getCurrentDateStr() {
        final SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String dateStr = sdf_yyyyMMdd.format(date);
        return dateStr;
    }

    /**返回当前日期.
     */
    public static int getCurrentDateInt() {
        final SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String dateStr = sdf_yyyyMMdd.format(date);
        int back = Integer.parseInt(dateStr);
        return back;
    }

    /**返回当前年份.
     */
    public static int getCurrentYearInt() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**返回昨天对应的横线型式.
     */
    public static String getLastDateHengxian() {
        final SimpleDateFormat sdf_yyyy_MM_dd = new SimpleDateFormat(
            "yyyy-MM-dd");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        String dateStr = sdf_yyyy_MM_dd.format(cal.getTime());
        return dateStr;
    }

}
