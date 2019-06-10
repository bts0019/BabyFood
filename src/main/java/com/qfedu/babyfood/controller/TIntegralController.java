package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.entity.TGift;
import com.qfedu.babyfood.entity.TUserGift;
import com.qfedu.babyfood.service.TIntegralService;
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
@RequestMapping("/tIntegral")
@Api(value = "积分模块", tags = "积分模块")
public class TIntegralController {

    @Autowired
    private TIntegralService tIntegralService;

    @RequestMapping(value = "/checkRealCode.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "检查获取防伪码", notes = "判断防伪码是否正确 realCode: 防伪码 checkCode 验证码")
    public R checkRealCode(String realCode, String checkCode){

        //从session域里获取生成的验证吗信息

        try {
            //判断防伪码是否存在
            tIntegralService.getRealCode("张三", checkCode, realCode);
            return R.setOK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.setERROR("error", e.getMessage());
        }
    }


    @RequestMapping(value = "/getIntegral.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "防伪验证获取积分", notes = "判断防伪码是否正确 realCode: 防伪码 checkCode 验证码")
    public R getIntegral(String realCode, String checkCode){

        //获取session域里的防伪验证码

        //获取用户信息

        try {
            //通过防伪验证码获取积分
            tIntegralService.getIntegralByRealCode(1, "张三", checkCode, realCode);
            return R.setOK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.setERROR("error", e.getMessage());
        }

    }

    @RequestMapping(value = "/getChangeIntegral.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "礼品兑换减少积分", notes = "礼品兑换信息")
    public R getChangeIntegral(TGift tGift){

        try {
            tIntegralService.getChangeIntegral(tGift, 1);
            return R.setOK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.setERROR("error", e.getMessage());
        }
    }

    @RequestMapping(value = "/addIntegralQuestion.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加积分问题", notes = "添加积分问题 question 问题")
    public R addIntegralQuestion(String question){

        //获取用户id
        try {

            //添加问题
            tIntegralService.addIntegralQuestion(question, 1);
            return R.setOK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.setERROR("error", e.getMessage());
        }



    }
}

