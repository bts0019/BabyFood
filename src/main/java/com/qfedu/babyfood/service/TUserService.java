package com.qfedu.babyfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.qfedu.babyfood.entity.TUser;
import com.qfedu.babyfood.vo.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TUserService extends IService<TUser> {
    /**
     * 检查用户输入验证码
     * @param myCode
     * @param userCode
     * @return
     */
    R checkCode(String myCode,String userCode,String userLogin);

}
