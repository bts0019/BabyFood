package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TExperience;
import com.baomidou.mybatisplus.service.IService;
import com.qfedu.babyfood.vo.VExperience;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TExperienceService extends IService<TExperience> {

    // 分类查询产品试用心得
    public List<VExperience> queryExperienceByTypeName(String typeName);

    // 查询单个产品的试用心得
    public List<VExperience> queryExperienceByProductId(int productId);

}
