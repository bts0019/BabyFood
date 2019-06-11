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

import java.util.*;

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

    @Autowired(required = false)
    private TUserMapper tUserMapper;

    @Autowired(required = false)
    private TBabyMapper tBabyMapper;

    @Override
    public R checkEmail(String myCode, String userCode,String userLogin) {
        List<TUser> user =  new ArrayList<TUser>();
        Map<String,Object> map =  new HashMap<>();
        map.put("email",userLogin);
        user =  tUserMapper.selectByMap(map);
        if (!user.isEmpty() ){
            return R.setERROR("用户邮箱已经被注册！",null);
        }
        R r = checkCode(myCode,userCode);
        if (r.getCode() ==1){
            return R.setOK("",null);
        }else {
            return R.setERROR("验证码错误！",null);
        }
    }

    @Override
    public R checkPhone(String myCode, String userCode, String userLogin, String code,String smscode) {
        List<TUser> user =  new ArrayList<TUser>();
        Map<String,Object> map =  new HashMap<>();
        R r = checkCode(myCode,userCode);
        map.put("phone",userLogin);
        user =  tUserMapper.selectByMap(map);
        if (!user.isEmpty() ){
            return R.setERROR("用户手机号已经被注册！",null);
        }
        if (r.getCode() !=1){
            return R.setERROR("验证码错误！",null);
        }
        if (!smscode.equals(code)){
            return R.setERROR("手机验证码输入错误！",null);
        }
        return R.setOK();
    }

    @Override
    public R registerByPhone(String myCode, String userCode, String userLogin, String code, String smscode) {
        R r = new R();
        r = checkPhone( myCode,userCode, userLogin, code,smscode);
        TUser user = new TUser();
        user.setPhone(userLogin);
        user.setStatus(0);
        if (r.getCode()==1){
            return addUser(user,null);
        }else if (r.getMsg().equals("用户手机号已经被注册！")){
            return R.setOK();
        }else {
            return R.setERROR("登录失败！",null);
        }
    }

    @Override
    public R checkCode(String myCode, String userCode) {
        if(myCode.equalsIgnoreCase(userCode)){
            return R.setOK("",true);
        }else {
            return R.setERROR("验证码错误！",false);
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
    public R login(String userlogin, String password) {
        List<TUser> user =  new ArrayList<TUser>();
        Map<String,Object> map =  new HashMap<>();
        if(userlogin.endsWith(".com")){
            map.put("email",userlogin);
        }else if(userlogin.length()==11){
            map.put("phone",userlogin);
        }else {
            map.put("userName",userlogin);
        }
        user =  tUserMapper.selectByMap(map);
        if (userlogin.endsWith(".com")&& user.isEmpty() ){
            return R.setERROR("用户邮箱不存在！",null);
        }else if(userlogin.length()==11 &&user.isEmpty() ){
            return R.setERROR("用户手机号不存在！请注册",null);
        }else if(user.isEmpty()){
            return R.setERROR("用户名不存在！请注册",null);
        }else if(!user.get(0).getPassword().equals(password)) {
            return R.setERROR("密码错误！",null);
        }else {
            return R.setOK("登录成功！",user.get(0).getUserName());
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

    @Override
    public TUser selectByBabyId(Integer babyId) {

        return tUserMapper.selectByBabyId(babyId);
    }

    @Override
    public R updateFlagTimeByBabyId(TUser tUser) {

        tUser.setFlagTime(new Date());
        tUserMapper.updateFlagTime(tUser);
        return R.setOK("OK", null);
    }

}
