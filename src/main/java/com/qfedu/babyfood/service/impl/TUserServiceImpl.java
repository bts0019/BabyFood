package com.qfedu.babyfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfedu.babyfood.dao.TBabyMapper;
import com.qfedu.babyfood.dao.TUserMapper;
import com.qfedu.babyfood.entity.TBaby;
import com.qfedu.babyfood.entity.TUser;
import com.qfedu.babyfood.service.TUserService;
import com.qfedu.babyfood.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private TBabyMapper tBabyMapper;

    @Override
    public R checkCode(String myCode, String userCode,String userLogin) {
        if(myCode.equalsIgnoreCase(userCode)){
            return R.setOK("",userLogin);
        }else {
            return R.setERROR("验证码错误！",null);
        }
    }

    @Override
    public R addUser(TUser user, TBaby baby) {
        if (user.getStatus() == 0){
            tUserMapper.insert(user);
            return R.setOK("添加成功！",null);
        }else{
            //tBabyMapper.
            int babyId = tBabyMapper.insertBaby(baby);
            user.setBabyId(babyId);
            tUserMapper.insert(user);
            return R.setOK("添加成功！",null);
        }

    }

    @Override
    public R findUserByEmail(String email) {
        if (tUserMapper.selectByemail(email) != null){
            return R.setERROR("邮箱已被注册",tUserMapper.selectByemail(email));
        }else{
            return R.setOK();
        }
    }

    @Override
    public R login(String email, String password) {
        TUser user = tUserMapper.selectByemail(email);
        if (user == null){
            return R.setERROR("用户邮箱不存在！",null);
        }else if(!user.getPassword().equals(password)) {
            return R.setERROR("密码错误！",null);
        }else {
            return R.setOK("登录成功！",user.getUserName());
        }
    }

    @Override
    public R findUserByName(String username) {
        return R.setOK("",tUserMapper.selectByName(username));
    }

    @Override
    public R updateUserByUsername(TUser user) {
        TUser user1 = tUserMapper.selectByName(user.getUserName());
        if (!user1.getPassword().equals(user.getPassword())){
            return R.setERROR("密码错误！",user1);
        }else{
            tUserMapper.updateById(user);
            return R.setOK("",tUserMapper.selectByName(user.getUserName()));
        }
    }

    @Override
    public R uploadImage(String imageurl, String username) {
        TUser user = tUserMapper.selectByName(username);
        user.setImage(imageurl);
        return R.setOK("添加成功！",tUserMapper.updateById(user));
    }

}
