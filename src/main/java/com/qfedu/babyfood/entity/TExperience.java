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
@TableName("t_experience")
public class TExperience extends Model<TExperience> {

    private static final long serialVersionUID = 1L;

    /**
     * 心得id
     */
    @TableId(value = "experienceId", type = IdType.AUTO)
    private Integer experienceId;
    /**
     * 心得内容
     */
    private String experience;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 产品id
     */
    private String productId;


    public Integer getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    protected Serializable pkVal() {
        return this.experienceId;
    }

    @Override
    public String toString() {
        return "TExperience{" +
        "experienceId=" + experienceId +
        ", experience=" + experience +
        ", userId=" + userId +
        ", productId=" + productId +
        "}";
    }
}
