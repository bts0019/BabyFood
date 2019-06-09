package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TUserGift;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-06
 */
public interface TUserGiftMapper extends BaseMapper<TUserGift> {

    /**
     * 添加用户和兑换的积分信息表
     * @param tUserGift
     */
    void inertUserGift(TUserGift tUserGift);
}
