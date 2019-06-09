package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfedu.babyfood.vo.VoQuestionAnswer;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TQuestionService extends IService<TQuestion> {

    /**
     * 获取所有问题和答案用户数据
     * @return
     */
    List<VoQuestionAnswer> getAllQuestionAnswer();

    /**
     * 通过用户id获取问题和答案数据
     * @return
     */
    List<VoQuestionAnswer> getAllQuestionAnswerByUserId(Integer userId);
}
