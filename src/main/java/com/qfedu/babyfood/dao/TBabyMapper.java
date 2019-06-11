package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TBaby;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TBabyMapper extends BaseMapper<TBaby> {

    /**
     *添加宝贝
     * @param baby
     * @return
     * 返回添加宝贝的id
     */
    int insertBaby(TBaby baby);

    // 添加宝宝信息
    public void addTBaby(TBaby tBaby);

    // 查询所有宝宝的信息
    public List<TBaby> queryAll();

    // 通过宝宝ID查找点赞数
    public TBaby queryById(Integer babyId);

    // 通过宝宝ID修改点赞数
    public void updateByBabyId(TBaby tBaby);

    // 查询个人宝宝及点赞信息
    public List<TBaby> queryBabyByUserId(int userId);
}
