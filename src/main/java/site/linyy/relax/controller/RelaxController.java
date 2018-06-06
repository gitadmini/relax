package site.linyy.relax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import site.linyy.relax.common.FileUtil;

@Controller
public class RelaxController {

    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    @ResponseBody
    public boolean collect(String path) throws IOException {

        FileUtil.writeOneLine("./list.txt", path);
        return true;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(String path) throws IOException {

        FileUtil.deleteLine("./list.txt", path);
        return true;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, String path, String search)
            throws IOException {

        if (path == null) {
            List<Map<String, String>> list = FileUtil.getList("./list.txt",
                search);
            model.addAttribute("menu", null);
            model.addAttribute("crumb", null);
            model.addAttribute("list", list);
        } else {
            List<Map<String, String>> list = FileUtil.getList(path, search);
            model.addAttribute("menu", path.split(":")[0]);
            model.addAttribute("crumb", getList(path));
            model.addAttribute("list", list);
        }
        return "relax";
    }

    private List<Map<String, String>> getList(String path) {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String[] paths = path.split("\\");
        for (int i = 0; i < paths.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", paths[i]);
            String p = "";
            for (int j = 0; j < i; j++) {
                p += paths[j] + "\\";
            }
            map.put("path", p);
            list.add(map);
        }
        return list;
    }

}
