package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TTypeMapper extends BaseMapper<TType> {

    // 查找所有人群分类
    public List<TType> queryAll();
}
