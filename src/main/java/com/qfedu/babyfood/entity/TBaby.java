package com.qfedu.babyfood.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
@TableName("t_baby")
public class TBaby extends Model<TBaby> {

    private static final long serialVersionUID = 1L;

    /**
     * 宝宝id
     */
    @TableId(value = "babyId", type = IdType.AUTO)
    private Integer babyId;
    /**
     * 宝宝的名字
     */
    private String name;
    /**
     * 宝宝的生日
     */
    private Date birth;
    /**
     * 宝宝的性别
     */
    private String gender;
    /**
     * 宝宝的点赞数
     */
    private Integer pretty;
    /**
     * 预产期
     */
    private Date expectTime;
    /**
     * 宝宝的图片url
     */
    private String image;


    public Integer getBabyId() {
        return babyId;
    }

    public void setBabyId(Integer babyId) {
        this.babyId = babyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPretty() {
        return pretty;
    }

    public void setPretty(Integer pretty) {
        this.pretty = pretty;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    protected Serializable pkVal() {
        return this.babyId;
    }

    @Override
    public String toString() {
        return "TBaby{" +
        "babyId=" + babyId +
        ", name=" + name +
        ", birth=" + birth +
        ", gender=" + gender +
        ", pretty=" + pretty +
        ", expectTime=" + expectTime +
        ", image=" + image +
        "}";
    }
}
