package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.dao.TDetailintegralMapper;
import com.qfedu.babyfood.dao.TProductMapper;
import com.qfedu.babyfood.dao.TUserMapper;
import com.qfedu.babyfood.entity.TDetailintegral;
import com.qfedu.babyfood.entity.TIntegral;
import com.qfedu.babyfood.dao.TIntegralMapper;
import com.qfedu.babyfood.entity.TProduct;
import com.qfedu.babyfood.entity.TUser;
import com.qfedu.babyfood.service.TIntegralService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Service
public class TIntegralServiceImpl extends ServiceImpl<TIntegralMapper, TIntegral> implements TIntegralService {

    //产品
    @Autowired
    private TProductMapper tProductMapper;

    // 积分
    @Autowired
    private TIntegralMapper tIntegralMapper;

    //积分明细
    @Autowired
    private TDetailintegralMapper tDetailintegralMapper;

    // 用户
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public boolean getRealCode(String checkCode, String userCheckCode, String realCode) throws Exception {

        //判断验证码是否正确
        if(!checkCode.equals(userCheckCode)){
            throw new Exception("验证码输入错误");
        }

        //查询防伪码是否正确
        if(null == tProductMapper.selectByRealCode(realCode)){
            throw new Exception("该防伪码不存在");
        }

        return true;
    }

    @Override
    public void getIntegralByRealCode(Integer userId, String checkCode, String userCheckCode, String realCode) throws Exception {

        //先验证
        boolean realCode1 = getRealCode(checkCode, userCheckCode, realCode);

        //修改用户积分
        if(realCode1 == true){
            TProduct tProduct = tProductMapper.selectByRealCode(realCode);
            //修改用户积分
            tIntegralMapper.updateIntegralByUserId(userId, tProduct.getScore());

            //插入积分明细表
            //创建积分表
            TDetailintegral tDetailintegral = new TDetailintegral();
            tDetailintegral.setIntegralId(null);
            tDetailintegral.setCreateTime(new Date());


            tDetailintegral.setIntegralId(tUserMapper.selectByUserId(userId).getIntegralId());
            tDetailintegral.setSource("防伪验证码获取");
            tDetailintegral.setNum(tProduct.getScore());
            tDetailintegral.setStatus(1);
            tDetailintegral.setImage(null);
            //生成积分明细表
            tDetailintegralMapper.insertIntegral(tDetailintegral);
        }

    }
}
