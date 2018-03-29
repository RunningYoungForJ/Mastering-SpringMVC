package masterSpringMvc.chapter5.controller;

import masterSpringMvc.chapter5.Repository.UserRepository;
import masterSpringMvc.chapter5.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yangkun on 2018/3/27.
 */
@RestController
@RequestMapping(value = "/api")
@Secured("ROLE_ADMIN")
public class UserApiController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "/user/{email}",method = RequestMethod.PUT)
    public User updateUser(@PathVariable String email,@RequestBody User user){
        return userRepository.save(email,user);
    }

    @RequestMapping(value = "/user/{email}",method = RequestMethod.DELETE)
    public void delete(@PathVariable String email){
        userRepository.delete(email);
    }

}
