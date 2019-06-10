package com.qfedu.babyfood.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.babyfood.entity.TCity;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TCityMapper extends BaseMapper<TCity> {

    public List<TCity> selectByParentCityId(int parentCityId);

}
