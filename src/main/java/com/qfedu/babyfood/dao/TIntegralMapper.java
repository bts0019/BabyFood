package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TDetailintegral;
import com.qfedu.babyfood.entity.TIntegral;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TIntegralMapper extends BaseMapper<TIntegral> {

    /**
     * 通过用户id修改用户的积分
     * @param userId
     * @param intrgral
     */
    void updateIntegralByUserId(@Param("userId") Integer userId, @Param("integral") Integer intrgral);


}
