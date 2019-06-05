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
@TableName("t_brand")
public class TBrand extends Model<TBrand> {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId(value = "brandId", type = IdType.AUTO)
    private Integer brandId;
    /**
     * 品牌名字
     */
    private String brandName;


    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    protected Serializable pkVal() {
        return this.brandId;
    }

    @Override
    public String toString() {
        return "TBrand{" +
        "brandId=" + brandId +
        ", brandName=" + brandName +
        "}";
    }
}
