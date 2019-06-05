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
@TableName("t_product_brand_type")
public class TProductBrandType extends Model<TProductBrandType> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品，品牌，类型关联表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 产品idi
     */
    private Integer productId;
    /**
     * 品牌id
     */
    private Integer brandId;
    /**
     * 类型id
     */
    private Integer typeId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TProductBrandType{" +
        "id=" + id +
        ", productId=" + productId +
        ", brandId=" + brandId +
        ", typeId=" + typeId +
        "}";
    }
}
