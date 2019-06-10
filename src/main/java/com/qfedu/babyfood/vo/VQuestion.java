package com.qfedu.babyfood.vo;

import java.util.Date;

public class VQuestion {
    /**
     * 问题id
     */
    private Integer questionId;
    /**
     * 问题内容
     */
    private String questtion;
    /**
     * 答案
     */
    private String answer;
    /**
     * 用户名
     */
    private String userName;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
