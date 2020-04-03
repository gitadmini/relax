package site.linyy.relax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** just an example.
 */
@Controller
public class ExampleController {

    @RequestMapping("/brush")
    public String brush() {
        return "brush";
    }

    @RequestMapping("/speech")
    public String speech(){
        return "speech";
    }

}
