package cn.sheep3.service;

import cn.sheep3.entity.User;
import cn.sheep3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by sheep3 on 2017/10/17.
 */
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            return null;
        }

        return (User) auth.getPrincipal();
    }
}
