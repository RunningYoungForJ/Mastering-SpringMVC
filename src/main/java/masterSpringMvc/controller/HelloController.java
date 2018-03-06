package masterSpringMvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangkun on 2018/3/6.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(@RequestParam(value = "name",defaultValue = "world")String userName, Model model){
        model.addAttribute("message","Hello, "+userName);
        return "resultPage";
    }
}
