package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TBaby;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TBabyMapper extends BaseMapper<TBaby> {

    /**
     *添加宝贝
     * @param baby
     * @return
     * 返回添加宝贝的id
     */
    int insertBaby(TBaby baby);

}
