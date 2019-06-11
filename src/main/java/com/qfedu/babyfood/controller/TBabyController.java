package com.qfedu.babyfood.controller;


import com.qfedu.babyfood.common.CommonInfo;
import com.qfedu.babyfood.entity.TBaby;
import com.qfedu.babyfood.service.TBabyService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Controller
@RequestMapping("/tBaby")
@Api(value = "点赞宝宝",tags = "点赞宝宝")//修饰是类，表示类做什么
public class TBabyController {

    @Autowired
    private TBabyService tBabyService;

    @ApiOperation(value = "查询全部宝宝", notes = "这是一个实现全部查询的方法")
    @RequestMapping(value = "/queryAll.do", method = RequestMethod.GET)
    @ResponseBody
    public R queryAll() {
        return tBabyService.queryAll();
    }

    @ApiOperation(value = "上传宝宝照片", notes = "这是一个实现上传宝宝照片的方法")
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    @ResponseBody
    public R addTBaby(MultipartFile upfile, TBaby tBaby) {

        if (!upfile.isEmpty()) {
            String path = "D:/photos";
            // 获取上传的文件名
            String filename = upfile.getOriginalFilename();
            filename = UUID.randomUUID()+filename.substring( filename.lastIndexOf('.'),filename.length());
            // 判断文件夹是否存在，不存在，创建
            File pFile = new File(path);
            if (!pFile.canExecute()) {
                pFile.mkdirs();
            }
            File file  = new File(path, filename);
            try {
                // 保存文件
                upfile.transferTo(new File(path + filename));
                tBaby.setImage(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tBabyService.addTBaby(tBaby);
    }

    @ApiOperation(value = "点赞", notes = "这是一个实现点赞的方法")
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public R updateById(Integer babyId) {
        return tBabyService.updateById(babyId);
    }


    @ApiOperation(value = "个人宝宝及点赞信息展示", notes = "个人宝宝及点赞信息展示")
    @RequestMapping(value = "/queryBabyByUserId.do", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public R queryBabyByUserId(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute(CommonInfo.LOGIN_USER);

        return R.setOK("OK", tBabyService.queryBabyByUserId(userName));
    }
}

