package masterSpringMvc.chapter4.profile;

import masterSpringMvc.chapter3.dto.ProfileForm;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangkun on 2018/3/19.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserProfileSession implements Serializable {
    private String twitterHandler;
    private String email;
    private LocalDate birthDate;
    private List<String> tastes=new ArrayList<>();

    public void setForm(ProfileForm profileForm){
        this.twitterHandler=profileForm.getTwitterHandler();
        this.email=profileForm.getEmail();
        this.birthDate=profileForm.getBirthDate();
        this.tastes=profileForm.getTastes();
    }

    public ProfileForm toForm(){
        ProfileForm profileForm=new ProfileForm();
        profileForm.setTwitterHandler(this.twitterHandler);
        profileForm.setEmail(this.email);
        profileForm.setBirthDate(this.birthDate);
        profileForm.setTastes(tastes);
        return profileForm;
    }
}
