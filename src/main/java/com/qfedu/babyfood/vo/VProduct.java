package com.qfedu.babyfood.vo;

import com.qfedu.babyfood.entity.TProductimage;
import lombok.Data;

import java.util.List;

@Data
public class VProduct {
    private Integer productId;
    private String productImagId;
    private String productName;
    private String address;
    private String quality;
    private String warnning;
    private String jdUrl;
    private String typeName;

    private String suitAge;
    private String norms;
    private String eatMethod;
    private String eatCapacity;
    private String storage;
    private String remark;
//  private Integer productImageId;
    private List<TProductimage> proUrl;
    private Integer status;


}
