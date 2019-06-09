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
@TableName("t_knowledge")
public class TKnowledge extends Model<TKnowledge> {

    private static final long serialVersionUID = 1L;

    /**
     * 育儿知识ID
     */
    @TableId(value = "knowledgeId", type = IdType.AUTO)
    private Integer knowledgeId;
    /**
     * 标题
     */
    private String knowledgeTitle;
    /**
     * 内容
     */
    private String knowledgeContext;
    /**
     * 关联详情
     */
    private String knowledgeUrl;


    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledgeTitle() {
        return knowledgeTitle;
    }

    public void setKnowledgeTitle(String knowledgeTitle) {
        this.knowledgeTitle = knowledgeTitle;
    }

    public String getKnowledgeContext() {
        return knowledgeContext;
    }

    public void setKnowledgeContext(String knowledgeContext) {
        this.knowledgeContext = knowledgeContext;
    }

    public String getKnowledgeUrl() {
        return knowledgeUrl;
    }

    public void setKnowledgeUrl(String knowledgeUrl) {
        this.knowledgeUrl = knowledgeUrl;
    }

    @Override
    protected Serializable pkVal() {
        return this.knowledgeId;
    }

    @Override
    public String toString() {
        return "TKnowledge{" +
        "knowledgeId=" + knowledgeId +
        ", knowledgeTitle=" + knowledgeTitle +
        ", knowledgeContext=" + knowledgeContext +
        ", knowledgeUrl=" + knowledgeUrl +
        "}";
    }
}
