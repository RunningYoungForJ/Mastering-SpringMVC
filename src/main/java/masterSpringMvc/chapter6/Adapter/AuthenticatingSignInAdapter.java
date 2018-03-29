package masterSpringMvc.chapter6.Adapter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Collection;

/**
 * Created by yangkun on 2018/3/29.
 */
@Component
public class AuthenticatingSignInAdapter implements SignInAdapter {
    public static void authenticate(Connection<?> connection){
        UserProfile userProfile=connection.fetchUserProfile();
        String userName=userProfile.getUsername();
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(String.format("User %s %s connected.",userProfile.getFirstName(),userProfile.getLastName()));
    }

    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        authenticate(connection);
        return null;
    }
}
