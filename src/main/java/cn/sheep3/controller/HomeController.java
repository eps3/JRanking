package cn.sheep3.controller;

import cn.sheep3.entity.User;
import cn.sheep3.model.RegisterForm;
import cn.sheep3.repository.UserRepository;
import cn.sheep3.service.UserService;
import cn.sheep3.util.PassWordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Random;

/**
 * Created by sheep3 on 2017/10/17.
 */
@Slf4j
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerPost(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        log.info("收到注册用 _ >" + registerForm);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user = new User();
        user.setName(registerForm.getUsername());
        user.setRole("USER");
        user.setPassword(registerForm.getPassword());
        user.setEmail(registerForm.getEmail());
        user.setDesc("");
        PassWordUtil.fuckUser(user);
        userService.addUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "login";
    }


    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> add() {
        return userRepository.findAll();
    }

}
