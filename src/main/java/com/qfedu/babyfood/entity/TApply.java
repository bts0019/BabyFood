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
@TableName("t_apply")
public class TApply extends Model<TApply> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "applyId", type = IdType.AUTO)
    private Integer applyId;
    private Integer productId;
    private Integer userId;
    private String postAddress;
    private String postCode;
    private String phone;
    private String remark;


    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.applyId;
    }

    @Override
    public String toString() {
        return "TApply{" +
        "applyId=" + applyId +
        ", productId=" + productId +
        ", userId=" + userId +
        ", postAddress=" + postAddress +
        ", postCode=" + postCode +
        ", phone=" + phone +
        ", remark=" + remark +
        "}";
    }
}
