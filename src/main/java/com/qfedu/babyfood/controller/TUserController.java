package com.qfedu.babyfood.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.qfedu.babyfood.common.CommonInfo;
import com.qfedu.babyfood.entity.TBaby;
import com.qfedu.babyfood.entity.TUser;
import com.qfedu.babyfood.service.TUserService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Api(value = "用户操作",tags = "用户登录、注册、生成验证码")//修饰是类，表示类做什么
@Controller
@RequestMapping("/tUser")
public class TUserController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    TUserService tUserService;

    /**
     * 生成验证码，并将验证码字段放到session的vrifyCode中
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @CrossOrigin//允许跨域
    @ApiOperation(value = "生成验证码",notes = "这是一个生成验证码的方法")
    @RequestMapping(value = "user/defaultKaptcha.do",method = RequestMethod.GET)
    @ResponseBody
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @CrossOrigin//允许跨域
    @ApiOperation(value = "检查验证码时候正确",notes = "这是一个实现新闻新增的方法，需要参数信息是用户的邮箱或者手机号和验证码")
    @RequestMapping(value = "user/imgvrifyControllerDefaultKaptcha.do",method = RequestMethod.POST)
    @ResponseBody
    public R imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String vrifyCode, String userLogin){
        ModelAndView andView = new ModelAndView();
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");

       return tUserService.checkCode(captchaId,vrifyCode,userLogin);
    }
    @CrossOrigin//允许跨域
    @ApiOperation(value = "用户注册",notes = "这是一个实现添加用户的方法，需要参数信息是用户的用户名、密码、姓名、城市id（到县区的id）、联系地址、现状")
    @RequestMapping(value = "user/registerUser.do",method = RequestMethod.POST)
    @ResponseBody
    public R registerUser(TUser user){


        return tUserService.addUser(user,null);
    }
    @CrossOrigin//允许跨域
    @ApiOperation(value = "用户注册且用户已有孩子",notes = "这是一个实现添加用户的方法，需要参数信息是用户的用户名、密码、姓名、城市id（到县区的id）、联系地址、现状以及孩子信息")
    @RequestMapping(value = "user/registerUserAndBaby.do",method = RequestMethod.POST)
    @ResponseBody
    public R registerUserAndBaby(TUser user, TBaby baby){

        return tUserService.addUser(user,baby);
    }

    @CrossOrigin//允许跨域
    @ApiOperation(value = "用户注册时检查用户邮箱时候已经注册过",notes = "这是一个实现查询用户名的方法，需要参数信息是用户的用户名")
    @RequestMapping(value = "user/findUserByName.do",method = RequestMethod.GET)
    @ResponseBody
    public R findUserByUsername(String email){
        return tUserService.findUserByName(email);
    }

    @CrossOrigin//允许跨域
    @ApiOperation(value = "用户登录",notes = "这是一个用户登录的方法，需要参数信息是用户的邮箱和密码")
    @RequestMapping(value = "user/login.do",method = RequestMethod.GET)
    @ResponseBody
    public R login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,String email, String password){
        R r = tUserService.login(email,password);
        if (r.getCode() == 1){
            httpServletRequest.getSession().setAttribute(CommonInfo.LOGIN_USER, r.getData());
        }
        return  r;
    }
}

