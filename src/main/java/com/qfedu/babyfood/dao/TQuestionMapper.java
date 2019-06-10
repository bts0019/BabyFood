package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TQuestion;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qfedu.babyfood.vo.VQuestion;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TQuestionMapper extends BaseMapper<TQuestion> {

    // 通过人群名称来查询所有的问题列表
    public List<VQuestion> queryAllByTypeName(String name);

    // 提交新问题
    public void add(TQuestion tQuestion);
}
