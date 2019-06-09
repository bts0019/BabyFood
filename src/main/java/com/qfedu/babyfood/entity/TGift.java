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
@TableName("t_gift")
public class TGift extends Model<TGift> {

    private static final long serialVersionUID = 1L;

    /**
     * 礼品id
     */
    @TableId(value = "gift", type = IdType.AUTO)
    private Integer gift;
    /**
     * 礼品名字
     */
    private String giftName;
    /**
     * 兑换所需积分
     */
    private Integer score;
    /**
     * 已兑换数量
     */
    private Integer offerNum;
    /**
     * 库存
     */
    private Integer stack;
    /**
     * 适应人群
     */
    private String suitAge;


    public Integer getGift() {
        return gift;
    }

    public void setGift(Integer gift) {
        this.gift = gift;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getOfferNum() {
        return offerNum;
    }

    public void setOfferNum(Integer offerNum) {
        this.offerNum = offerNum;
    }

    public Integer getStack() {
        return stack;
    }

    public void setStack(Integer stack) {
        this.stack = stack;
    }

    public String getSuitAge() {
        return suitAge;
    }

    public void setSuitAge(String suitAge) {
        this.suitAge = suitAge;
    }

    @Override
    protected Serializable pkVal() {
        return this.gift;
    }

    @Override
    public String toString() {
        return "TGift{" +
        "gift=" + gift +
        ", giftName=" + giftName +
        ", score=" + score +
        ", offerNum=" + offerNum +
        ", stack=" + stack +
        ", suitAge=" + suitAge +
        "}";
    }
}
