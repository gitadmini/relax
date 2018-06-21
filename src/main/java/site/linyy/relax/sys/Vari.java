package site.linyy.relax.sys;

import java.util.ArrayList;
import java.util.List;

// 静态变量
public class Vari {

    public static List<String> msgList = new ArrayList<String>();

    // 设置msg，timeout秒后失效
    public static void setMsgList(String msg, int timeout) {
        msgList.add(msg);
        new Thread(() -> {
            try {
                Thread.sleep(timeout * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msgList.remove(msg);
        }).start();
    }

}
