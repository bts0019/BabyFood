package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.service.TProductService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(value = "查询产品", tags = "查询产品")
@Controller
@ResponseBody
@RequestMapping("/tProduct")
public class TProductController {

    @Autowired
    private TProductService tProductService;

    @ApiOperation(value = "根据类型查询产品", notes = "根据类型查询产品")
    @RequestMapping("/queryByTypeName.do")
    @CrossOrigin
    public R queryAllByTypeName(String typeName){
        return R.setOk("OK", tProductService.queryAllByTypeName(typeName));
    }

    @ApiOperation(value = "查询指定id产品详细信息", notes = "查询指定id产品详细信息")
    @RequestMapping("/queryByProductId.do")
    @CrossOrigin
    public R queryByProductId(int productId){
        return  R.setOk("OK", tProductService.queryByProductId(productId));
    }

    @ApiOperation(value = "查询试用产品信息", notes = "查询试用产品信息")
    @RequestMapping("/queryByStatusAndTypeName.do")
    @CrossOrigin
    public R queryProductByStatusAndTypeName(int status, String typeName){
        return R.setOk("OK", tProductService.queryProductByStatusAndTypeName(status, typeName));
    }



}

