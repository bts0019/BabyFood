package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TProductMapper extends BaseMapper<TProduct> {


    /**
     * 通过防伪码查询数据
     * @param realCode
     * @return
     */
    TProduct selectByRealCode(String realCode);

}
