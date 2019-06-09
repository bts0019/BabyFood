package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TGift;
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
public interface TGiftMapper extends BaseMapper<TGift> {

    /**
     * 通过用户id获取兑换产品的数据
     * @param userId
     * @return
     */
    List<TGift> selectByUserId(Integer userId);
}
