package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.entity.TGift;
import com.qfedu.babyfood.service.TGiftService;
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
@RequestMapping("/tGift")
@Api(value = "关于礼品的功能模块", tags = "礼品模块")
public class TGiftController {


    @Autowired
    private TGiftService tGiftService;

    @RequestMapping(value = "/getGift.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取礼品信息", notes = "查询所有的礼品的信息")
    public R getGift(){
        return R.setOK("OK", tGiftService.list());
    }

    @RequestMapping(value = "/getAllByUserId.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取兑换的礼品信息", notes = "查询用户所有兑换的礼品的信息")
    public R getAllByUserId(){

        //1 获取用户的信息
        return R.setOK("OK", tGiftService.getAllByUsreId(1));
    }

}

