package masterSpringMvc.chapter6.controller;

import masterSpringMvc.chapter6.Adapter.AuthenticatingSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by yangkun on 2018/3/29.
 */
@Controller
public class SignupController {
    private final ProviderSignInUtils signInUtils;

    @Autowired
    public SignupController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository connectionRepository){
        signInUtils=new ProviderSignInUtils(connectionFactoryLocator,connectionRepository);
    }

    @RequestMapping("/signup")
    public String signup(WebRequest request){
        Connection<?> connection=signInUtils.getConnectionFromSession(request);
        if (connection!=null){
            AuthenticatingSignInAdapter.authenticate(connection);
            signInUtils.doPostSignUp(connection.getDisplayName(),request);
        }
        return "redirect:/profile";
    }

}
