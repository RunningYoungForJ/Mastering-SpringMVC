package masterSpringMvc.chapter4.controller;

import masterSpringMvc.chapter4.profile.UserProfileSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by yangkun on 2018/3/21.
 */
@Controller
public class HomeController {
    private UserProfileSession userProfileSession;

    @Autowired
    public HomeController(UserProfileSession userProfileSession) {
        this.userProfileSession = userProfileSession;
    }

    public String home(){
        List<String> tastes=userProfileSession.getTastes();
        if (tastes.isEmpty()){
            return "redirect:/profile";
        }
        return "redirect:/search/mixed;keywords=" +String.join(",",tastes);
    }
}
