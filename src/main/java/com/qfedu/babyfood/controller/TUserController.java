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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.UUID;

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


   // @Value("${cbs.imagesPath}")
    //private String theSetDir; //全局配置文件中设置的图片的路径

   /* @GetMapping("/{page}")
    public String toPate(@PathVariable("page") String page)
    {
        return page;
    }*/
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

    @CrossOrigin//允许跨域
    @ApiOperation(value = "用户修改",notes = "这是一个用户修改自己信息的方法，需要参数信息是用户的信息")
    @RequestMapping(value = "user/updateUserByUsername.do",method = RequestMethod.POST)
    @ResponseBody
    public R updateUserByUsername(TUser user){
        //String username = (String) httpServletRequest.getSession().getAttribute(CommonInfo.LOGIN_USER);
        return tUserService.updateUserByUsername(user);
    }


    @CrossOrigin//允许跨域
    @ApiOperation(value = "登录用户查找",notes = "这是一个用户自己信息的方法，需要参数信息是session中的登录用户的信息")
    @RequestMapping(value = "user/findUserByUsername.do",method = RequestMethod.POST)
    @ResponseBody
    public R findUserByusername(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String username = (String) httpServletRequest.getSession().getAttribute(CommonInfo.LOGIN_USER);
        return R.setOK("",tUserService.findUserByName(username));
    }


    @CrossOrigin//允许跨域
    @ApiOperation(value = "文件上传",notes = "这是一个文件上传的方法，需要参数信息是enctype=multipart/form-data中的登录用户的信息")
    //@RequestMapping(value = "user/findUserByUsername.do",method = RequestMethod.POST)
    @RequestMapping(value = "/fileUploadController.do",method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(MultipartFile filename, Model model,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
        String parentDirPath ="F:/mypc/images/";
                //theSetDir.substring(theSetDir.indexOf(':')+1, theSetDir.length()); //通过设置的那个字符串获得存放图片的目录路径
        String fileName = filename.getOriginalFilename();

        //根据传输的图片后缀生成随机文件名
        fileName =UUID.randomUUID()+fileName.substring( fileName.lastIndexOf('.'),fileName.length());
        File parentDir = new File(parentDirPath);
        if(!parentDir.exists()) //如果那个目录不存在先创建目录
        {
            parentDir.mkdir();
        }

        filename.transferTo(new File(parentDirPath + fileName)); //全局配置文件中配置的目录加上文件名
        //登录的用户名
        String username = (String)httpServletRequest.getSession().getAttribute(CommonInfo.LOGIN_USER);
        //将图片路径录入用户的image字段中
        //TUser user = tUserService.findUserByName(username);
        tUserService.uploadImage(parentDirPath+fileName,username);
        model.addAttribute("pic_name", fileName);

        return "show";
    }
}

