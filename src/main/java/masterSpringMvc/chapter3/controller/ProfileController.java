package masterSpringMvc.chapter3.controller;

import masterSpringMvc.chapter3.dto.ProfileForm;
import masterSpringMvc.chapter3.utils.USLocalDateFormatter;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by yangkun on 2018/3/12.
 */
@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm){
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm){
        System.out.println("save ok "+" "+profileForm.toString());
        return "redirect:/profile";
    }

    @ModelAttribute("dateFormat")
    public String localFormat(Locale locale){
        return USLocalDateFormatter.getPattern(locale);
    }

}
