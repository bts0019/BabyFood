package com.qfedu.babyfood.entity;

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
@TableName("t_type")
public class TType extends Model<TType> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    @TableId(value = "typeId", type = IdType.AUTO)
    private Integer typeId;
    /**
     * 类型名
     */
    private String typeName;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }

    @Override
    public String toString() {
        return "TType{" +
        "typeId=" + typeId +
        ", typeName=" + typeName +
        "}";
    }
}
