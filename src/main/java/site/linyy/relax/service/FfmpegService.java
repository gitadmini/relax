package site.linyy.relax.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import site.linyy.relax.sys.Vari;

@Service
public class FfmpegService {

    // 存储执行中的cmd
    public static List<String> cmdList = new ArrayList<String>();

    @Value("${ffmpeg.path}")
    String ffPath;

    // 将视频文件转换为safari可读的mp4类型
    // 系统要先安装好ffmpeg
    @Async
    public void parseToMp4(String path)
            throws IOException, InterruptedException {

        String newPath = path + ".生成";
        // libxvid 比 libx264 生成的文件小，因此效率高，但是画面差
        String cmd = ffPath + "ffmpeg.exe -y -i " + path
                + " -f mp4 -vcodec libxvid -movflags faststart " + newPath;
        if (cmdList.contains(cmd)) {
            return; // 正在执行
        }
        cmdList.add(cmd);
        Process process = Runtime.getRuntime().exec(cmd);
        InputStream stderr = process.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            // System.out.println(line);
        }
        int result = process.waitFor();
        // 执行完毕
        cmdList.remove(cmd);
        Vari.setMsgList(path + "生成结束!", 10); // 10秒生存时间的消息，供前端获取
    }

}
