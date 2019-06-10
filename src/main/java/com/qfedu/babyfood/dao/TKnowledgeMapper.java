package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TKnowledge;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
