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
@TableName("t_city")
public class TCity extends Model<TCity> {

    private static final long serialVersionUID = 1L;

    /**
     * 城市id
     */
    @TableId(value = "cityId", type = IdType.AUTO)
    private Integer cityId;
    /**
     * 城市名字
     */
    private String cityName;
    /**
     * 所关联的父级id 这是个自联属性
     */
    private Integer parentCityId;


    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getParentCityId() {
        return parentCityId;
    }

    public void setParentCityId(Integer parentCityId) {
        this.parentCityId = parentCityId;
    }

    @Override
    protected Serializable pkVal() {
        return this.cityId;
    }

    @Override
    public String toString() {
        return "TCity{" +
        "cityId=" + cityId +
        ", cityName=" + cityName +
        ", parentCityId=" + parentCityId +
        "}";
    }
}
