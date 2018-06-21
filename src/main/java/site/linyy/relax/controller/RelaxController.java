package site.linyy.relax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import site.linyy.relax.common.BeautyUtil;
import site.linyy.relax.common.FileUtil;
import site.linyy.relax.common.ListUtil;
import site.linyy.relax.service.FfmpegService;
import site.linyy.relax.sys.Vari;

@Controller
public class RelaxController {

    // 收藏文件的地址
    public final static String LIST_PATH = "./list.txt";

    @Autowired
    FfmpegService ffmpegService;

    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    @ResponseBody
    public String msg() {

        List<String> list = Vari.msgList;
        if (list.size() > 0) {
            StringBuffer back = new StringBuffer("");
            for (int i = 0; i < list.size(); i++) {
                back.append(list.get(i)).append("\n");
            }
            back.deleteCharAt(back.length() - 1);
            return back.toString();
        }
        return "";
    }

    @RequestMapping(value = "/parseToMp4", method = RequestMethod.POST)
    @ResponseBody
    public String parseToMp4(String path)
            throws IOException, InterruptedException {

        ffmpegService.parseToMp4(path);
        return path + "生成中...";
    }

    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    public boolean collect(String path, String search) throws IOException {

        FileUtil.writeOneLine(LIST_PATH,
            path + "<" + (search == null ? "" : search));
        return true;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public boolean delete(String path, String search) throws IOException {

        FileUtil.deleteLine(LIST_PATH,
            path + "<" + (search == null ? "" : search));
        return true;
    }

    // 收藏页
    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    public String collectPage(Model model) throws IOException {

        List<String> lineList = FileUtil.readAllLine(LIST_PATH);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        if (lineList != null && lineList.size() > 0) {
            for (int i = 0; i < lineList.size(); i++) {
                String str = lineList.get(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("path", str.split("<")[0]);
                map.put("search",
                    str.split("<").length <= 1 ? "" : str.split("<")[1]);
                map.put("showPath", getShowPath(str));
                list.add(map);
            }
        }
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                public int compare(Map<String, String> o1,
                        Map<String, String> o2) {
                    String path1 = o1.get("path");
                    String path2 = o2.get("path");
                    int resultPath = path1.compareTo(path2);
                    if (resultPath != 0) {
                        return resultPath;
                    } else {
                        return o1.get("search").compareTo(o2.get("search"));
                    }
                }
            });
        }
        model.addAttribute("list", list);
        model.addAttribute("menuFolder", FileUtil.getMenuFolder());
        model.addAttribute("beauty", BeautyUtil.getByRandom());
        return "collect";
    }

    // 文件夹页
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, String path, String search)
            throws IOException {

        List<Map<String, String>> list = FileUtil.getList(path, search);
        model.addAttribute("crumb", getList(path));
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                public int compare(Map<String, String> o1,
                        Map<String, String> o2) {
                    String flag1 = o1.get("flag");
                    String flag2 = o2.get("flag");
                    int resultFlag = flag1.compareTo(flag2);
                    if (resultFlag != 0) {
                        return -resultFlag;
                    } else {
                        return o1.get("name").compareTo(o2.get("name"));
                    }
                }
            });
        }
        model.addAttribute("list", list);
        String showPath = getShowPath(path);
        model.addAttribute("showPath", showPath);
        model.addAttribute("path", path);
        model.addAttribute("search", search);
        model.addAttribute("menuFolder", FileUtil.getMenuFolder());
        model.addAttribute("beauty", BeautyUtil.getByRandom());
        return "relax";
    }

    private String getShowPath(String path) {

        String[] paths = path.split("\\\\");
        if (path.endsWith("\\")) {
            if (paths.length <= 4) {
                return path;
            } else {
                String newPath = paths[0] + "\\" + paths[1] + "\\**\\"
                        + paths[paths.length - 2] + "\\"
                        + paths[paths.length - 1] + "\\";
                return newPath;
            }
        } else {
            if (paths.length <= 5) {
                return path;
            } else {
                String newPath = paths[0] + "\\" + paths[1] + "\\**\\"
                        + paths[paths.length - 3] + "\\"
                        + paths[paths.length - 2] + "\\"
                        + paths[paths.length - 1];
                return newPath;
            }
        }
    }

    private List<Map<String, String>> getList(String path) {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String[] paths = path.split("\\\\");
        for (int i = 0; i < paths.length - 1; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", paths[i]);
            String p = "";
            for (int j = 0; j <= i; j++) {
                p += paths[j] + "\\";
            }
            map.put("path", p);
            map.put("showPath", getShowPath(p));
            list.add(map);
        }
        list = ListUtil.reverse(list);
        return list;
    }

}
