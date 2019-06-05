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
@TableName("t_product")
public class TProduct extends Model<TProduct> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "productId", type = IdType.AUTO)
    private Integer productId;
    /**
     * 产品内容
     */
    private String productName;
    /**
     * 京东旗舰店连接
     */
    private String jdUrl;
    /**
     * 产品图片
     */
    private String productImagId;
    /**
     * 适应人群
     */
    private String suitAge;
    /**
     * 包装规格
     */
    private String norms;
    /**
     * 食用方法
     */
    private String eatMethod;
    /**
     * 食量
     */
    private String eatCapacity;
    /**
     * 贮藏方法
     */
    private String storage;
    /**
     * 原产国
     */
    private String address;
    /**
     * 保质期
     */
    private String quality;
    /**
     * 温馨提示
     */
    private String warnning;
    /**
     * 简介
     */
    private String remark;
    /**
     * 防伪码
     */
    private String realCode;
    /**
     * 积分
     */
    private Integer score;
    /**
     * 用户id
     */
    private Integer userId;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getJdUrl() {
        return jdUrl;
    }

    public void setJdUrl(String jdUrl) {
        this.jdUrl = jdUrl;
    }

    public String getProductImagId() {
        return productImagId;
    }

    public void setProductImagId(String productImagId) {
        this.productImagId = productImagId;
    }

    public String getSuitAge() {
        return suitAge;
    }

    public void setSuitAge(String suitAge) {
        this.suitAge = suitAge;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getEatMethod() {
        return eatMethod;
    }

    public void setEatMethod(String eatMethod) {
        this.eatMethod = eatMethod;
    }

    public String getEatCapacity() {
        return eatCapacity;
    }

    public void setEatCapacity(String eatCapacity) {
        this.eatCapacity = eatCapacity;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWarnning() {
        return warnning;
    }

    public void setWarnning(String warnning) {
        this.warnning = warnning;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRealCode() {
        return realCode;
    }

    public void setRealCode(String realCode) {
        this.realCode = realCode;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.productId;
    }

    @Override
    public String toString() {
        return "TProduct{" +
        "productId=" + productId +
        ", productName=" + productName +
        ", jdUrl=" + jdUrl +
        ", productImagId=" + productImagId +
        ", suitAge=" + suitAge +
        ", norms=" + norms +
        ", eatMethod=" + eatMethod +
        ", eatCapacity=" + eatCapacity +
        ", storage=" + storage +
        ", address=" + address +
        ", quality=" + quality +
        ", warnning=" + warnning +
        ", remark=" + remark +
        ", realCode=" + realCode +
        ", score=" + score +
        ", userId=" + userId +
        "}";
    }
}
