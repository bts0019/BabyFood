package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.entity.TGift;
import com.qfedu.babyfood.dao.TGiftMapper;
import com.qfedu.babyfood.service.TGiftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Service
public class TGiftServiceImpl extends ServiceImpl<TGiftMapper, TGift> implements TGiftService {

    @Autowired
    private TGiftMapper tGiftMapper;

    @Override
    public List<TGift> getAllByUsreId(Integer userId) {
        return tGiftMapper.selectByUserId(userId);
    }
}
