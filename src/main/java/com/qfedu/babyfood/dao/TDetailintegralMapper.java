package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TDetailintegral;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.babyfood.entity.TIntegral;

import java.util.List;

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


    /**
     *  通过用户id查询用户的积分细节
     * @param userId
     * @return
     */
    List<TDetailintegral> getAllByUserID(Integer userId);

    /**
     * 得到用户积分收入的数据
     * @param userId
     * @return 积分细节数据
     */
    List<TDetailintegral> getGainByUserID(Integer userId);

    /**
     * 得到用户支出的积分的数据
     * @param userId
     * @return
     */
    List<TDetailintegral> getLoseByUserID(Integer userId);

}
