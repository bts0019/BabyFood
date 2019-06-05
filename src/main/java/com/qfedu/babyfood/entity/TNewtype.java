package com.qfedu.babyfood.entity;

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
@TableName("t_newtype")
public class TNewtype extends Model<TNewtype> {

    private static final long serialVersionUID = 1L;

    /**
     * 新闻类型id
     */
    private Integer newTypeId;
    /**
     * 新闻类型
     */
    private String newTypeName;


    public Integer getNewTypeId() {
        return newTypeId;
    }

    public void setNewTypeId(Integer newTypeId) {
        this.newTypeId = newTypeId;
    }

    public String getNewTypeName() {
        return newTypeName;
    }

    public void setNewTypeName(String newTypeName) {
        this.newTypeName = newTypeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.newTypeId;
    }

    @Override
    public String toString() {
        return "TNewtype{" +
        "newTypeId=" + newTypeId +
        ", newTypeName=" + newTypeName +
        "}";
    }
}
