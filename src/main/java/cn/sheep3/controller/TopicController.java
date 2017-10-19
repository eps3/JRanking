package cn.sheep3.controller;

import cn.sheep3.entity.Topic;
import cn.sheep3.exception.KnownSystemException;
import cn.sheep3.model.RegisterForm;
import cn.sheep3.model.TopicForm;
import cn.sheep3.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 主题相关页面
 * <p>
 * Created by sheep3 on 2017/10/17.
 */
@Slf4j
@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = {"/topic"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("topicList",topicService.getAllTopic());
        return "topic/topic";
    }

    @RequestMapping(value = {"/topic"}, method = RequestMethod.POST)
    public String topicPost(@Valid TopicForm topicForm, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("result", "你的输入有误请检查你的输入");
                model.addAttribute("topicForm", topicForm);
                return "topic/add";
            }
            topicService.addTopic(topicForm);
            model.addAttribute("result", "添加成功");
        } catch (KnownSystemException e) {
            model.addAttribute("msg", e.getMessage());
            return "topic/add";
        }
        return "topic/add";
    }

    @RequestMapping(value = {"/topic/{topic_id}/follow"}, method = RequestMethod.GET)
    public String follow(@PathVariable Long topic_id, Model model) {
        try {
            Topic topic = topicService.followTopic(topic_id);
            model.addAttribute("msg", topic.getName() + " 订阅成功 !");
            return "redirect:/card";
        } catch (KnownSystemException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/topic";
        }
    }

    @RequestMapping(value = {"/topic/add"}, method = RequestMethod.GET)
    public String add() {
        return "topic/add";
    }


}
