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
     * 检查用户输入验证码
     * @param myCode
     * @param userCode
     * @return
     */
    R checkCode(String myCode,String userCode,String userLogin);

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
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    R login(String email,String password);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    R findUserByName(String username);

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
}
