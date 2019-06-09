package com.qfedu.babyfood.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@TableName("t_answer")
public class TAnswer extends Model<TAnswer> {

    private static final long serialVersionUID = 1L;

    /**
     * 问题的id
     */
    @TableId(value = "answerId", type = IdType.AUTO)
    private Integer answerId;
    /**
     * 问题的内容
     */
    private String answer;


    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    protected Serializable pkVal() {
        return this.answerId;
    }

    @Override
    public String toString() {
        return "TAnswer{" +
        "answerId=" + answerId +
        ", answer=" + answer +
        "}";
    }
}
