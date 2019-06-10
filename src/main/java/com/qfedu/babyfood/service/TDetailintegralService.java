package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TDetailintegral;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TDetailintegralService extends IService<TDetailintegral> {

    /**
     * 通过userId获取积分明细
     * @param usreId
     * @return
     */
    List<TDetailintegral> getAllDetailIntegralByUserId(Integer usreId);

    /**
     * 通过用户id获取积分细节的数据
     * @param userId
     * @return
     */
    List<TDetailintegral> getGainDetailIntegralByUserId(Integer userId);

    /**
     * 通过用户id支出积分细节的数据
     * @param userId
     * @return
     */
    List<TDetailintegral> getLoseDetailIntegralByUserId(Integer userId);
}
