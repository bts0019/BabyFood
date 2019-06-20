package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.service.TTypeService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/tType")
@Api(value = "查询人群分类", tags = "查询人群分类")
public class TTypeController {

    @Autowired
    private TTypeService tTypeService;

    @ApiOperation(value = "查询人群分类", notes = "这是一个查询所有人群分类的方法")
    @RequestMapping(value = "/queryAll.do", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public R queryAll() {
        return tTypeService.queryAll();
    }
}

