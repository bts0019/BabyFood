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
@TableName("t_productimage")
public class TProductimage extends Model<TProductimage> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品图片介绍id
     */
    @TableId(value = "productImagId", type = IdType.AUTO)
    private Integer productImagId;
    /**
     * 图片的关联路径
     */
    private String proUrl;
    /**
     * 产品id
     */
    private Integer productId;


    public Integer getProductImagId() {
        return productImagId;
    }

    public void setProductImagId(Integer productImagId) {
        this.productImagId = productImagId;
    }

    public String getProUrl() {
        return proUrl;
    }

    public void setProUrl(String proUrl) {
        this.proUrl = proUrl;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    protected Serializable pkVal() {
        return this.productImagId;
    }

    @Override
    public String toString() {
        return "TProductimage{" +
        "productImagId=" + productImagId +
        ", proUrl=" + proUrl +
        ", productId=" + productId +
        "}";
    }
}
