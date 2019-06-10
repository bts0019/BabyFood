package com.qfedu.babyfood.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.babyfood.entity.TKnowledge;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TKnowledgeMapper extends BaseMapper<TKnowledge> {

    // 查询所有育儿知识
    public List<TKnowledge> queryAll();
}
