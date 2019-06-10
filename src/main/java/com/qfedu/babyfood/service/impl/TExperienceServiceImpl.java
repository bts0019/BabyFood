package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.dao.TUserMapper;
import com.qfedu.babyfood.entity.TExperience;
import com.qfedu.babyfood.dao.TExperienceMapper;
import com.qfedu.babyfood.entity.TUser;
import com.qfedu.babyfood.service.TExperienceService;

import com.qfedu.babyfood.vo.VExperience;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TExperienceServiceImpl extends ServiceImpl<TExperienceMapper, TExperience> implements TExperienceService {

    @Autowired(required = false)
    private TExperienceMapper tExperienceDao;

    @Autowired(required = false)
    private TUserMapper tUserDao;

    // 分类查询产品试用心得
    @Override
    public List<VExperience> queryExperienceByTypeName(String typeName) {
        List<VExperience> list = tExperienceDao.queryExperienceByTypeName(typeName);
        return list;
    }

    // 查询单个产品的试用心得
    @Override
    public List<VExperience> queryExperienceByProductId(int productId) {
        List<VExperience> list = tExperienceDao.queryExperienceByProductId(productId);
        return list;
    }

    @Override
    public List<VExperience> queryExperienceByUserId(String typeName, String userName) {
        TUser tUser = tUserDao.selectByName(userName);
        List<VExperience> list = tExperienceDao.queryExperienceByUserId(tUser.getUserId(), typeName);
        return list;
    }
}
