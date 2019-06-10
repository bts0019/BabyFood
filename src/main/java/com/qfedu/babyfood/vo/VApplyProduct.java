package com.qfedu.babyfood.vo;

import lombok.Data;

@Data
public class VApplyProduct {
    private Integer userId;
    private Integer applyId;
    private Integer productId;
    private String productImageId;
    private String postAddress;
    private String posCode;
    private String phone;
    private String remark;

}
