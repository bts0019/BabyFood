package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.entity.TUser;
import com.qfedu.babyfood.dao.TUserMapper;
import com.qfedu.babyfood.service.TUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qfedu.babyfood.vo.R;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesion
 * @since 2019-06-06
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Override
    public R checkCode(String myCode, String userCode,String userLogin) {
        if(myCode.equalsIgnoreCase(userCode)){
            return R.setOK("",userLogin);
        }else {
            return R.setERROR("验证码错误！",null);
        }
    }
}
