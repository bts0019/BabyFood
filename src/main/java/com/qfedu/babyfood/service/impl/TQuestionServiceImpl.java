package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.entity.TQuestion;
import com.qfedu.babyfood.dao.TQuestionMapper;
import com.qfedu.babyfood.service.TQuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qfedu.babyfood.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfedu.babyfood.vo.VoQuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

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
public class TQuestionServiceImpl extends ServiceImpl<TQuestionMapper, TQuestion> implements TQuestionService {

    @Autowired
    private TQuestionMapper tQuestionMapper;

    @Override
    public List<VoQuestionAnswer> getAllQuestionAnswerByUserId(Integer userId) {
        return tQuestionMapper.selectAllQuestionAnswerByUserId(userId);
    }

    @Override
    public List<VoQuestionAnswer> getAllQuestionAnswer() {
        return tQuestionMapper.selectAllQuestionAnswer();
    }
    @Autowired(required = false)
    private TQuestionMapper tQuestionMapper;

    @Override
    public R queryAllByTypeName(String name) {
        return R.setOK("OK", tQuestionMapper.queryAllByTypeName(name));
    }

    @Override
    public R addQuestion(@RequestBody TQuestion tQuestion) {
        tQuestion.setCreateTime(new Date());
        tQuestionMapper.add(tQuestion);
        return R.setOK("OK", null);
    }
}
