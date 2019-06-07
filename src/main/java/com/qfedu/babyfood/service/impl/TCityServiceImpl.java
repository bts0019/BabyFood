package com.qfedu.babyfood.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qfedu.babyfood.dao.TCityMapper;
import com.qfedu.babyfood.entity.TCity;
import com.qfedu.babyfood.service.TCityService;
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
public class TCityServiceImpl extends ServiceImpl<TCityMapper, TCity> implements TCityService {

    @Autowired
    private TCityMapper tCityMapper;

    @Override
    public List<TCity> selectByParentCityId(int parentCityId) {
        return tCityMapper.selectByParentCityId(parentCityId);
    }
}
