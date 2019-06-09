package com.qfedu.babyfood.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Controller
@RequestMapping("/tAnswer")
public class TAnswerController {

    /**
     * 测试
     * @param map
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HashMap<String, Object> map){
        map.put("hello", "么么么");
        return "index";
    }



}

