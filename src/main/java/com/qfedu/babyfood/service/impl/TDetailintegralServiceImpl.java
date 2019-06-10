package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.entity.TDetailintegral;
import com.qfedu.babyfood.dao.TDetailintegralMapper;
import com.qfedu.babyfood.service.TDetailintegralService;
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
public class TDetailintegralServiceImpl extends ServiceImpl<TDetailintegralMapper, TDetailintegral> implements TDetailintegralService {

    @Autowired
    private TDetailintegralMapper tDetailintegralMapper;

    @Override
    public List<TDetailintegral> getGainDetailIntegralByUserId(Integer userId) {
        return tDetailintegralMapper.getGainByUserID(userId);
    }

    @Override
    public List<TDetailintegral> getLoseDetailIntegralByUserId(Integer userId) {
        return tDetailintegralMapper.getLoseByUserID(userId);
    }

    @Override
    public List<TDetailintegral> getAllDetailIntegralByUserId(Integer usreId) {

        List<TDetailintegral> allByUserID = tDetailintegralMapper.getAllByUserID(usreId);
        return allByUserID;

    }
}
