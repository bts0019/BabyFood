package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.entity.TDetailintegral;
import com.qfedu.babyfood.service.TDetailintegralService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/tDetailintegral")
@Api(value = "积分明细模块", tags = "积分明细模块")
public class TDetailintegralController {

    @Autowired
    private TDetailintegralService tDetailintegralService;


    @RequestMapping(value = "/getDetail.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询用户的所有的积分明细", notes = "查询所有的积分明细数据")
    public R getAll(){

        //1. 获取用户id


        try {
            List<TDetailintegral> allDetailIntegralByUserId = tDetailintegralService.getAllDetailIntegralByUserId(1);
            return R.setOK("OK", allDetailIntegralByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.setERROR("OK", e.getMessage());
        }

    }

    @RequestMapping(value = "/getGain.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询用户的收入的积分明细", notes = "查询所有收入的积分明细数据")
    public R getGain(){

        //1. 获取用户id

        try {
            List<TDetailintegral> allDetailIntegralByUserId = tDetailintegralService.getGainDetailIntegralByUserId(1);
            return R.setOK("OK", allDetailIntegralByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.setERROR("OK", e.getMessage());
        }

    }

    @RequestMapping(value = "/getLose.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询用户的支出的积分明细", notes = "查询所有支出的积分明细数据")
    public R getLose(){

        //1. 获取用户id

        try {
            List<TDetailintegral> allDetailIntegralByUserId = tDetailintegralService.getLoseDetailIntegralByUserId(1);
            return R.setOK("OK", allDetailIntegralByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.setERROR("OK", e.getMessage());
        }

    }




}

