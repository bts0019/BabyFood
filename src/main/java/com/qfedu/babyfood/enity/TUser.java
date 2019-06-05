package com.qfedu.babyfood.enity;

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
@TableName("t_user")
public class TUser extends Model<TUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 地址
     */
    private String address;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 宝宝id
     */
    private Integer babyId;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 积分id
     */
    private Integer integralId;
    /**
     * 角色
     */
    private Integer role;
    /**
     * 图片
     */
    private String image;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBabyId() {
        return babyId;
    }

    public void setBabyId(Integer babyId) {
        this.babyId = babyId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "TUser{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", password=" + password +
        ", realName=" + realName +
        ", address=" + address +
        ", phone=" + phone +
        ", email=" + email +
        ", status=" + status +
        ", babyId=" + babyId +
        ", cityId=" + cityId +
        ", integralId=" + integralId +
        ", role=" + role +
        ", image=" + image +
        "}";
    }
}
