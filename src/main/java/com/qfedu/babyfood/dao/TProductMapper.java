package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TApply;
import com.qfedu.babyfood.entity.TProduct;

import com.qfedu.babyfood.vo.VApplyProduct;
import com.qfedu.babyfood.vo.VProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TProductMapper extends BaseMapper<TProduct> {

    // 通过类型查询产品（人群类型）
    public List<VProduct> queryAllByTypeName(String typeName);

    // 查询单个产品详细信息
    public List<VProduct> queryByProductId(int productId);

    // 通过状态和类型查询产品（使用状态和人群类型）
    public List<VProduct> queryProductByStatusAndTypeName(@Param("status") int status, @Param("typeName") String typeName);



    /**
     * 通过防伪码查询数据
     * @param realCode
     * @return
     */
    TProduct selectByRealCode(String realCode);


    /**
     * 个人试用申请
     * @param tApply
     */
    public void tryApply(TApply tApply);

    /**
     * 查询个人申请试用的产品
     * @param userId
     * @return
     */
    public List<VApplyProduct> queryApplyProductByUserId(int userId);

}
