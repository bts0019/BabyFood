package com.qfedu.babyfood.entity;

import java.util.Date;
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
@TableName("t_detailintegral")
public class TDetailintegral extends Model<TDetailintegral> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分细节表的Id（相当于积分消费表）
     */
    private Integer detailIntegralId;
    /**
     * 积分细节的图片解释
     */
    private String image;
    /**
     * 积分来源
     */
    private String source;
    /**
     * 积分的状态 0 支出 1 收入 2 冻结
     */
    private Integer status;
    /**
     * 积分数
     */
    private Integer num;
    /**
     * 积分明创建来源
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 积分表
     */
    private Integer integralId;


    public Integer getDetailIntegralId() {
        return detailIntegralId;
    }

    public void setDetailIntegralId(Integer detailIntegralId) {
        this.detailIntegralId = detailIntegralId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
    }

    @Override
    protected Serializable pkVal() {
        return this.detailIntegralId;
    }

    @Override
    public String toString() {
        return "TDetailintegral{" +
        "detailIntegralId=" + detailIntegralId +
        ", image=" + image +
        ", source=" + source +
        ", status=" + status +
        ", num=" + num +
        ", createTime=" + createTime +
        ", remark=" + remark +
        ", integralId=" + integralId +
        "}";
    }
}
