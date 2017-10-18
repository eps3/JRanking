package cn.sheep3.controller;

import cn.sheep3.exception.KnownSystemException;
import cn.sheep3.model.RegisterForm;
import cn.sheep3.repository.UserRepository;
import cn.sheep3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 用户相关页面
 * <p>
 * Created by sheep3 on 2017/10/17.
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerPost(@Valid RegisterForm registerForm, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("result", "你的输入有误请检查你的输入");
                model.addAttribute("registerForm", registerForm);
                return "register";
            }
            userService.register(registerForm);
        } catch (KnownSystemException e) {
            model.addAttribute("msg", e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "错误的用户名或密码!");
        }
        if (logout != null) {
            model.addAttribute("msg", "退出成功!");
        }
        return "login";
    }


}
