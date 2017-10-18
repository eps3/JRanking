package cn.sheep3.service;

import cn.sheep3.entity.User;
import cn.sheep3.exception.KnownSystemException;
import cn.sheep3.model.RegisterForm;
import cn.sheep3.repository.UserRepository;
import cn.sheep3.util.PassWordUtil;
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

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return (User) auth.getPrincipal();
    }

    public void register(RegisterForm registerForm) throws KnownSystemException {
        if (userRepository.findByName(registerForm.getUsername()) != null) {
            throw new KnownSystemException("该用户名已存在");
        }
        if (userRepository.findByEmail(registerForm.getEmail()) != null) {
            throw new KnownSystemException("该邮箱已存在");
        }
        User user = new User();
        user.setName(registerForm.getUsername());
        user.setRole("USER");
        user.setPassword(registerForm.getPassword());
        user.setEmail(registerForm.getEmail());
        user.setDesc("");
        PassWordUtil.fuckUser(user);
        addUser(user);
    }
}
