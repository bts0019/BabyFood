package com.qfedu.babyfood.enity;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("t_news")
public class TNews extends Model<TNews> {

    private static final long serialVersionUID = 1L;

    /**
     * 新闻ID
     */
    @TableId(value = "newId", type = IdType.AUTO)
    private Integer newId;
    /**
     * 新闻内容
     */
    private String contexet;
    /**
     * 新闻关联详情
     */
    private String url;
    /**
     * 新闻类型id
     */
    private Integer newTypeId;


    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public String getContexet() {
        return contexet;
    }

    public void setContexet(String contexet) {
        this.contexet = contexet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNewTypeId() {
        return newTypeId;
    }

    public void setNewTypeId(Integer newTypeId) {
        this.newTypeId = newTypeId;
    }

    @Override
    protected Serializable pkVal() {
        return this.newId;
    }

    @Override
    public String toString() {
        return "TNews{" +
        "newId=" + newId +
        ", contexet=" + contexet +
        ", url=" + url +
        ", newTypeId=" + newTypeId +
        "}";
    }
}
