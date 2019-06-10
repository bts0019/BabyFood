package com.qfedu.babyfood.controller;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.qfedu.babyfood.common.CommonInfo;
import com.qfedu.babyfood.entity.TApply;
import com.qfedu.babyfood.service.TProductService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Api(value = "查询产品", tags = "查询产品")
@Controller
@ResponseBody
@RequestMapping("/tProduct")
public class TProductController {

    @Autowired
    private TProductService tProductService;

    @ApiOperation(value = "根据类型查询产品", notes = "根据类型查询产品")
    @RequestMapping(value = "/queryByTypeName.do", method = RequestMethod.GET)
    @CrossOrigin
    public R queryAllByTypeName(){
        String typeName = "婴幼儿";

        return R.setOK("OK", tProductService.queryAllByTypeName(typeName));
    }

    @ApiOperation(value = "查询指定id产品详细信息", notes = "查询指定id产品详细信息")
    @RequestMapping(value = "/queryByProductId.do", method = RequestMethod.GET)
    @CrossOrigin
    public R queryByProductId(int productId){
        return  R.setOK("OK", tProductService.queryByProductId(productId));
    }

    @ApiOperation(value = "查询试用产品信息", notes = "查询试用产品信息")
    @RequestMapping(value = "/queryByStatusAndTypeName.do", method = RequestMethod.GET)
    @CrossOrigin
    public R queryProductByStatusAndTypeName(int status, String typeName){
        return R.setOK("OK", tProductService.queryProductByStatusAndTypeName(status, typeName));
    }


    @ApiOperation(value = "个人申请试用", notes = "个人申请试用")
    @RequestMapping(value = "/tryApply.do", method = RequestMethod.GET)
    @CrossOrigin
    public R tryApply(TApply tApply){
        tProductService.tryApply(tApply);
        return R.setOK("OK", null);
    }


    @ApiOperation(value = "查询个人申请试用的产品", notes = "查询个人申请试用的产品")
    @RequestMapping(value = "/queryApplyProductByUserId.do", method = RequestMethod.GET)
    @CrossOrigin
    public R queryApplyProductByUserId(int userId){
        userId = 1;
        return R.setOK("OK", tProductService.queryApplyProductByUserId(userId));
    }









}

