package masterSpringMvc.chapter3.dto;

import masterSpringMvc.chapter3.date.PastLocalDate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangkun on 2018/3/12.
 */
public class ProfileForm {
    @Size(min = 2,max = 64)
    private String twitterHandler;
    @Email
    @NotEmpty
    private String email;
    @NotNull
    @PastLocalDate
    private LocalDate birthDate;

    private List<String> tastes=new ArrayList<>();

    public String getTwitterHandler() {
        return twitterHandler;
    }

    public void setTwitterHandler(String twitterHandler) {
        this.twitterHandler = twitterHandler;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getTastes() {
        return tastes;
    }

    public void setTastes(List<String> tastes) {
        this.tastes = tastes;
    }

    @Override
    public String toString() {
        return "ProfileForm{" +
                "twitterHandler='" + twitterHandler + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", tasts=" + tastes +
                '}';
    }
}
