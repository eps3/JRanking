package cn.sheep3.controller;

import cn.sheep3.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页
 * <p>
 * Created by sheep3 on 2017/10/17.
 */
@Slf4j
@Controller
public class CardController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = {"card"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("topicList", topicService.getAllFollowTopic());
        return "card/card";
    }
}
