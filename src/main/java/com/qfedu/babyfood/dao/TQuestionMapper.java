package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.babyfood.vo.VoQuestionAnswer;

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

    /**
     * 获取所有的用户的问题和答案数据
     * @return
     */
    List<VoQuestionAnswer> selectAllQuestionAnswer();

    /**
     * 获取相应用户的问题和答案数据
     * @param userId 用户id
     * @return
     */
    List<VoQuestionAnswer> selectAllQuestionAnswerByUserId(Integer userId);


}
