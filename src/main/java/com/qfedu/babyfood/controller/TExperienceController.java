package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.common.CommonInfo;
import com.qfedu.babyfood.service.TExperienceService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Api(value = "产品试用心得", tags = "产品试用心得")
@Controller
@ResponseBody
@RequestMapping("/tExperience")
public class TExperienceController {

    @Autowired
    private TExperienceService tExperienceService;

    @ApiOperation(value = "查询所有产品试用心得", notes = "查询所有产品试用心得")
    @RequestMapping(value = "/queryExperienceByTypeName.do", method = RequestMethod.GET)
    @CrossOrigin
    public R queryExperienceByTypeName(String typeName){
        return R.setOK("OK", tExperienceService.queryExperienceByTypeName(typeName));
    }

    @ApiOperation(value = "查询单个产品试用心得", notes = "查询单个产品试用心得")
    @RequestMapping(value = "/queryExperienceByProductId.do", method = RequestMethod.GET)
    @CrossOrigin
    public R queryExperienceByProductId(int productId){
        return R.setOK("OK", tExperienceService.queryExperienceByProductId(productId));
    }


    @ApiOperation(value = "查询个人申请产品的试用心得", notes = "查询个人申请产品的试用心得")
    @RequestMapping(value = "/queryExperienceByUserId.do", method = RequestMethod.GET)
    @CrossOrigin
    public R queryExperienceByUserId(String typeName, HttpServletRequest request){
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute(CommonInfo.LOGIN_USER);
        return R.setOK("OK", tExperienceService.queryExperienceByUserId(typeName, userName));
    }

}

