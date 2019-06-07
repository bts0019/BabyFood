package com.qfedu.babyfood.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.qfedu.babyfood.service.TUserService;
import com.qfedu.babyfood.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "user/defaultKaptcha",method = RequestMethod.GET)
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
    @RequestMapping(value = "user/imgvrifyControllerDefaultKaptcha",method = RequestMethod.POST)
    @ResponseBody
    public R imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String vrifyCode, String userLogin){
        ModelAndView andView = new ModelAndView();
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");

       return tUserService.checkCode(captchaId,vrifyCode,userLogin);
    }
}

