package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TDetailintegral;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TDetailintegralMapper extends BaseMapper<TDetailintegral> {

    /**
     * 插入积分信息
     * @param TDetailintegral
     */
    void insertIntegral(TDetailintegral TDetailintegral);
    
}
