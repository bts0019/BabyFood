package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.service.TExperienceService;
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
@Api(value = "查询产品试用心得", tags = "查询产品试用心得")
@Controller
@ResponseBody
@RequestMapping("/tExperience")
public class TExperienceController {

    @Autowired
    private TExperienceService tExperienceService;

    @ApiOperation(value = "查询所有产品试用心得", notes = "查询所有产品试用心得")
    @RequestMapping("/queryExperienceByTypeName.do")
    public R queryExperienceByTypeName(String typeName){
        return R.setOK("OK", tExperienceService.queryExperienceByTypeName(typeName));
    }

    @ApiOperation(value = "查询单个产品试用心得", notes = "查询单个产品试用心得")
    @RequestMapping("/queryExperienceByProductId.do")
    public R queryExperienceByProductId(int productId){
        return R.setOK("OK", tExperienceService.queryExperienceByProductId(productId));
    }

}

