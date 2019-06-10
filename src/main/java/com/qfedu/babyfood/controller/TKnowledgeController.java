package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.service.TKnowledgeService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Controller
@RequestMapping("/tKnowledge")
@Api(value = "查询育儿知识",tags = "育儿知识")//修饰是类，表示类做什么
public class TKnowledgeController {

    @Autowired
    private TKnowledgeService tKnowledgeService;

    @ApiOperation(value = "查询育儿知识", notes = "这是一个查询育儿知识的方法")
    @RequestMapping(value = "/queryAll.do", method = RequestMethod.GET)
    @ResponseBody
    public R queryAll() {
        return tKnowledgeService.queryAll();
    }
}

