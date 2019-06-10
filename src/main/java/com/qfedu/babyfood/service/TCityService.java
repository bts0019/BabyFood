package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TCity;
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
public interface TCityService extends IService<TCity> {
    /**
     * 通过城市上一级查询城市
     * @param parentCityId
     * 上一级id字段
     * @return
     * 城市集合
     */
    public List<TCity> selectByParentCityId(int parentCityId);

}
