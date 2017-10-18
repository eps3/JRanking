package cn.sheep3.controller;

import cn.sheep3.entity.User;
import cn.sheep3.repository.UserRepository;
import cn.sheep3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页
 * <p>
 * Created by sheep3 on 2017/10/17.
 */
@Slf4j
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> add() {
        return userRepository.findAll();
    }

}
