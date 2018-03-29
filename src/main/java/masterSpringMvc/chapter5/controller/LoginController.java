package masterSpringMvc.chapter5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yangkun on 2018/3/29.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String authenticate(){
        return "login";
    }
}
