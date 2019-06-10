package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TQuestion;
import com.baomidou.mybatisplus.service.IService;
import com.qfedu.babyfood.vo.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TQuestionService extends IService<TQuestion> {

    R queryAllByTypeName(String name);

    R addQuestion(TQuestion tQuestion);
}
