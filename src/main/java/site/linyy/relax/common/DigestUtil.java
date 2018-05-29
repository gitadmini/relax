package site.linyy.relax.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 加密.
 */
public class DigestUtil {

    public static String SHA1(String str) {
        // 指定sha1算法
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(str.getBytes());
        // 获取字节数组
        byte messageDigest[] = digest.digest();
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString().toUpperCase();

    }

}
