package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TIntegral;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TIntegralService extends IService<TIntegral> {

    /**
     *  查询防伪码是否正确
     * @param checkCode 传输的验证码
     * @param realCode 防伪码
     * @param  userCheckCode 用户输入的验证码
     * @return
     */
    boolean getRealCode(String checkCode, String userCheckCode, String realCode) throws Exception;

    /**
     *  通过验证防伪验证来获取积分
     * @param checkCode 验证码
     * @param userCheckCode 用户输入的验证码
     * @param realCode 防伪码
     * @param userId 用户id
     * @return
     */
    void getIntegralByRealCode(Integer userId, String checkCode, String userCheckCode, String realCode) throws Exception;

}
