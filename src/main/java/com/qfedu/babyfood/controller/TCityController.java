package com.qfedu.babyfood.controller;

import com.qfedu.babyfood.service.TCityService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(value = "城市操作",tags = "对城市进行的查找操作")
@Controller
@RequestMapping("/tCity")
public class TCityController {

    @Autowired
    private TCityService tCityService;

    @CrossOrigin//允许跨域
    @ApiOperation(value = "查询城市",notes = "这是一个查询城市的方法，需要参数信息是所要查询城市的上一级id，若为第一级则上一级是0")
    @RequestMapping(value = "city/findCityByParentId",method = RequestMethod.GET)
    @ResponseBody
    public R selectCity(int parentId){
       // Map<String,Object> map = new HashMap<>();
        //System.out.println(parentId+"***************************************");
        //map.put("parentCityId",parentId);
        //System.out.println(tCityService.selectByParentCityId(parentId));
        return R.setOK(null,tCityService.selectByParentCityId(parentId));
    }

}

