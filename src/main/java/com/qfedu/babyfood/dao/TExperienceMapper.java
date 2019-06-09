package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TExperience;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qfedu.babyfood.vo.VExperience;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TExperienceMapper extends BaseMapper<TExperience> {

    // 分类查询产品试用心得
    public List<VExperience> queryExperienceByTypeName(String typeName);

    // 查询单个产品的试用心得
    public List<VExperience> queryExperienceByProductId(int productId);

}
