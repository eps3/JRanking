package cn.sheep3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页
 * <p>
 * Created by sheep3 on 2017/10/17.
 */
@Slf4j
@Controller
public class RankController {


    @RequestMapping(value = {"rank"}, method = RequestMethod.GET)
    public String index() {
        return "rank/rank";
    }



}
