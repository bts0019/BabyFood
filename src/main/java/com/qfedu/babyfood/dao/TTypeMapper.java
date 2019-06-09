package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TTypeMapper extends BaseMapper<TType> {

    /**
     * 添加问题类型
     * @param typeName 类型名字
     * @return 返回自增后的id
     */
    int insertType(String typeName);

}
