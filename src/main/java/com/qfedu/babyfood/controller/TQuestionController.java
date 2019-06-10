package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.entity.TQuestion;
import com.qfedu.babyfood.service.TQuestionService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.qfedu.babyfood.service.TQuestionService;
import com.qfedu.babyfood.vo.R;
import com.qfedu.babyfood.vo.VoQuestionAnswer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Controller
@RequestMapping("/tQuestion")
@Api(value = "问题模块", tags = "问题模块")
public class TQuestionController {

    @Autowired
    private TQuestionService tQuestionService;

    @RequestMapping(value = "/getAllIntegralQuestion.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "积分问题查询", notes = "查询积分模块的问题和答案")
    public R getAllIntegralQuestion(){

        try {
            List<VoQuestionAnswer> allQuestionAnswer = tQuestionService.getAllQuestionAnswer();
            return R.setOK("OK", allQuestionAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            return R.setOK("error", e.getMessage());
        }

    }

    @RequestMapping(value = "/getAllIntegralQuestionByUserId.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "积分问题查询", notes = "查询积分模块的问题和答案")
    public R getAllIntegralQuestionByUserId(){

        try {
            List<VoQuestionAnswer> allQuestionAnswer = tQuestionService.getAllQuestionAnswerByUserId(1);
            return R.setOK("OK", allQuestionAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            return R.setOK("error", e.getMessage());
        }

    }

    @ApiOperation(value = "营养知识问答", notes = "这是一个查询所有营养知识问题的方法")
    @RequestMapping(value = "/queryAll.do", method = RequestMethod.GET)
    @ResponseBody
    public R queryAll(String name) {
        return tQuestionService.queryAllByTypeName(name);
    }

    @ApiOperation(value = "提交问题", notes = "这是一个提交问题的方法")
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    @ResponseBody
    public R addTQuestion(TQuestion tQuestion) {
        return tQuestionService.addQuestion(tQuestion);
    }
}

