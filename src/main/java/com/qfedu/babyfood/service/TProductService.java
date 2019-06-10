package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import com.qfedu.babyfood.vo.VProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TProductService extends IService<TProduct> {

    // 通过类型查询产品（人群类型）
    public List<VProduct> queryAllByTypeName(String typeName);

    // 查询单个产品详细信息
    public List<VProduct> queryByProductId(int productId);

    // 通过状态和类型查询产品（使用状态和人群类型）
    public List<VProduct> queryProductByStatusAndTypeName(int status, String typeName);

}
