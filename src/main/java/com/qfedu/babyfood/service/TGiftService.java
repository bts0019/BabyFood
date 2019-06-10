package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TGift;
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
public interface TGiftService extends IService<TGift> {


    /**
     * 通过用户id获取用户兑换的商品的数据
     * @param userId 用户的id
     * @return 返回兑换的礼品
     */
    List<TGift> getAllByUsreId(Integer userId);

}
