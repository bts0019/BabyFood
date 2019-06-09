package com.qfedu.babyfood.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfedu.babyfood.dao.*;
import com.qfedu.babyfood.entity.*;
import com.qfedu.babyfood.service.TIntegralService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //礼品类
    @Autowired
    private TGiftMapper tGiftMapper;

    //问题类型
    @Autowired
    private TTypeMapper tTypeMapper;

    //问题
    @Autowired
    private TQuestionMapper tQuestionMapper;

    //用户兑换表
    @Autowired
    private TUserGiftMapper tUserGiftMapper;

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
            tIntegralMapper.updateIntegralByUserId(userId, tProduct.getScore()+ tIntegralMapper.selectByUserID(userId).getTotal());

            //插入积分明细表
            //创建积分表
            TDetailintegral tDetailintegral = new TDetailintegral();
            tDetailintegral.setIntegralId(null);
            tDetailintegral.setCreateTime(new Date());


            tDetailintegral.setIntegralId(tUserMapper.selectById(userId).getIntegralId());
            tDetailintegral.setSource("防伪验证码获取");
            tDetailintegral.setNum(tProduct.getScore());
            tDetailintegral.setStatus(1);
            tDetailintegral.setImage(null);
            //生成积分明细表
            tDetailintegralMapper.insertIntegral(tDetailintegral);
        }

    }

    @Override
    public void getChangeIntegral(TGift tGift, Integer userId) throws Exception {

        //获取积分
        TIntegral tIntegral = tIntegralMapper.selectByUserID(userId);
        int score = tIntegral.getTotal();

        //判断是否还有礼品信息
        if(tGift.getStack() <= 0){
            throw new Exception("库存不足");
        }

        //对积分进行判断
        if(score < 0 || score < tGift.getScore()){
            throw new Exception("积分不足");
        }

        //修改用户的积分
        tIntegralMapper.updateIntegralByUserId(userId, score - tGift.getScore());

        //更改礼品的数据
        tGift.setOfferNum(tGift.getOfferNum() + 1);//供货量+1
        tGift.setStack(tGift.getStack() - 1);//库存 - 1
        tGiftMapper.updateById(tGift);

        //生成积分明细表
        TDetailintegral tDetailintegral = new TDetailintegral();
        tDetailintegral.setStatus(0);
        tDetailintegral.setCreateTime(new Date());
        tDetailintegral.setSource("兑换礼品");
        tDetailintegral.setIntegralId(tIntegral.getIntegralId());
        tDetailintegral.setNum(tGift.getScore());
        tDetailintegral.setRemark("兑换礼品支出");

        tDetailintegralMapper.insert(tDetailintegral);
        //生成礼品兑换表
        TUserGift tUserGift = new TUserGift();
        tUserGift.setCreateTime(new Date());
        tUserGift.setGiftId(tGift.getGift());
        tUserGift.setUserId(userId);
        tUserGiftMapper.inertUserGift(tUserGift);
    }


    @Override
    public void addIntegralQuestion(String question, Integer userId) {

        //判断积分类别是否存在

        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("typeName", "积分问题");
        List<TType> tTypes = tTypeMapper.selectByMap(columnMap);
        if(null == tTypes){
            int typeId = tTypeMapper.insertType("积分问题");
            tTypes.get(0).setTypeId(typeId);
        }

        //添加问题信息
        TQuestion tQuestion = new TQuestion();
        tQuestion.setCreateTime(new Date());
        tQuestion.setQuesttion(question);
        tQuestion.setTypeId(tTypes.get(0).getTypeId());
        tQuestion.setUserId(userId);

        tQuestionMapper.insert(tQuestion);
    }
}
