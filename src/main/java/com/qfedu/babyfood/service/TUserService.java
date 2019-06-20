package com.qfedu.babyfood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfedu.babyfood.entity.TBaby;
import com.qfedu.babyfood.entity.TUser;
import com.qfedu.babyfood.vo.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-06
 */
public interface TUserService extends IService<TUser> {
    /**
     * 通过邮箱注册检测用户邮箱以及验证码
     * @param myCode
     * @param userCode
     * @return
     */
    R checkEmail(String myCode,String userCode,String userLogin);

    /**
     * 通过手机号注册，检查验证码是否正确，检查用户手机号是否被注册，检查手机验证码是否正确
     * @param myCode
     * @param userCode
     * @param userLogin
     * @param code
     * @return
     */
    R checkPhone(String myCode,String userCode,String userLogin,String code,String smscode);

    /**
     * 通过手机号登录并且直接注册
     * @param myCode
     * @param userCode
     * @param userLogin
     * @param code
     * @param smscode
     * @return
     */
    R registerByPhone(String myCode,String userCode,String userLogin,String code,String smscode);

    /**
     * 检查验证码是否正确
     * @param myCode
     * @param userCode
     * @return
     */
    R checkCode(String myCode,String userCode);
    /**
     * 添加用户
     * @param user
     * @param baby
     * @return
     */
    R addUser(TUser user, TBaby baby);

    /**
     * 根据用户名查询用户
     * @param email
     * @return
     */
    R findUserByEmail(String email);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    R findUserByName(String username);


    /**
     * 用户登录
     * @param userlogin
     * @param password
     * @return
     */
    R login(String userlogin,String password);

    /**
     * 根据用户名更新用户信息
     * @param user
     * @return
     */
    R updateUserByUsername(TUser user);

    /**
     * 上传图片
     * @param imageurl
     * @param username
     * @return
     */
    R uploadImage(String imageurl,String username);

    /**
     * 查找用户上次点赞时间
     * @param babyId
     * @return
     */
    TUser selectByBabyId(Integer babyId);

    R updateFlagTimeByBabyId(TUser tUser);
}
