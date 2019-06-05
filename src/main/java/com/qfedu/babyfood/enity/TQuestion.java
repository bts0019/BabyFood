package com.qfedu.babyfood.enity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@TableName("t_question")
public class TQuestion extends Model<TQuestion> {

    private static final long serialVersionUID = 1L;

    /**
     * 问题id
     */
    @TableId(value = "questionId", type = IdType.AUTO)
    private Integer questionId;
    /**
     * 问题内容
     */
    private String questtion;
    /**
     * 答案
     */
    private Integer answerId;
    /**
     * 类型id
     */
    private Integer typeId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 产品id
     */
    private Integer productId;


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuesttion() {
        return questtion;
    }

    public void setQuesttion(String questtion) {
        this.questtion = questtion;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    protected Serializable pkVal() {
        return this.questionId;
    }

    @Override
    public String toString() {
        return "TQuestion{" +
        "questionId=" + questionId +
        ", questtion=" + questtion +
        ", answerId=" + answerId +
        ", typeId=" + typeId +
        ", userId=" + userId +
        ", createTime=" + createTime +
        ", productId=" + productId +
        "}";
    }
}
