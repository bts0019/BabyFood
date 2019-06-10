package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
    /**
     * 添加问题类型
     * @param typeName 类型名字
     * @return 返回自增后的id
     */
    int insertType(String typeName);

}
