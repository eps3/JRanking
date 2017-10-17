package cn.sheep3.config.security;

import cn.sheep3.entity.User;
import cn.sheep3.repository.UserRepository;
import cn.sheep3.service.UserService;
import cn.sheep3.util.PassWordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by sheep3 on 2017/10/17.
 */
@Slf4j
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        Object password = authentication.getCredentials();
        User user = null;
        user = userService.findByName(username);
        if(user == null){
            throw new BadCredentialsException("Username not found.");
        }
        //加密过程在这里体现
        if (! Objects.equals(PassWordUtil.getMD5(password + user.getSalt()), user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
