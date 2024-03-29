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
@TableName("t_integral")
public class TIntegral extends Model<TIntegral> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分表Id
     */
    @TableId(value = "integralId", type = IdType.AUTO)
    private Integer integralId;
    /**
     * 积分总数
     */
    private Integer total;
    /**
     * 可用积分
     */
    private Integer enable;
    /**
     * 冻结积分
     */
    private Integer disable;


    public Integer getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    @Override
    protected Serializable pkVal() {
        return this.integralId;
    }

    @Override
    public String toString() {
        return "TIntegral{" +
        "integralId=" + integralId +
        ", total=" + total +
        ", enable=" + enable +
        ", disable=" + disable +
        "}";
    }
}
