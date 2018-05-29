package site.linyy.relax.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import site.linyy.relax.common.FileUtil;

@Service
public class FileService {

    public void searchFile(Model model, String path, String search)
            throws IOException {

        List<Map<String, String>> list = searchFile(path, search);
        List<Map<String, String>> linkList = getLink(path);
        model.addAttribute("list", list);
        model.addAttribute("linkList", linkList);
    }

    // 将路径转化为面包屑
    public List<Map<String, String>> getLink(String path) {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String[] paths = path.split("\\");
        for (int i = 0; i < paths.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", paths[i]);
            String p = "";
            for (int j = 0; j <= i; j++) {
                p += paths[i] + "\\";
            }
            map.put("path", p);
            // TODO we should maybe add link
        }
        return list;
    }

    public List<Map<String, String>> searchFile(String path, String search)
            throws IOException {

        List<Map<String, String>> list = FileUtil.getList(path);
        if (StringUtils.isNotBlank(search) && list != null && list.size() > 0) {
            List<Map<String, String>> searchList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                String name = map.get("name");
                if (name.indexOf(search) > -1) {
                    searchList.add(map);
                }
            }
            return searchList;
        }
        return list;
    }

}
