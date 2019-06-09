package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.service.TIntegralService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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

    @RequestMapping("/checkRealCode.do")
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


    @RequestMapping("/getIntegral.do")
    @ResponseBody
    @ApiOperation(value = "获取积分", notes = "判断防伪码是否正确 realCode: 防伪码 checkCode 验证码")
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
}

