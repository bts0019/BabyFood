package com.qfedu.babyfood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@TableName("t_user_gift")
public class TUserGift extends Model<TUserGift> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户兑换记录表
     */
    @TableId(value = "user_giftId", type = IdType.AUTO)
    private Integer userGiftid;
    /**
     * 礼品id
     */
    private Integer giftId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getUserGiftid() {
        return userGiftid;
    }

    public void setUserGiftid(Integer userGiftid) {
        this.userGiftid = userGiftid;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.userGiftid;
    }

    @Override
    public String toString() {
        return "TUserGift{" +
        "userGiftid=" + userGiftid +
        ", giftId=" + giftId +
        ", userId=" + userId +
        ", createTime=" + createTime +
        "}";
    }
}
