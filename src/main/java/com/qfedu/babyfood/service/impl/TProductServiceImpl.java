package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.entity.TProduct;
import com.qfedu.babyfood.dao.TProductMapper;
import com.qfedu.babyfood.service.TProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qfedu.babyfood.vo.VProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Service
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct> implements TProductService {

    @Autowired(required = false)
    private TProductMapper tProductDao;

    // 通过类型查询产品（人群类型）
    @Override
    public List<VProduct> queryAllByTypeName(String typeName) {
        List<VProduct> list = tProductDao.queryAllByTypeName(typeName);
        return list;
    }

    // 查询单个产品详细信息
    @Override
    public List<VProduct> queryByProductId(int productId) {
        List<VProduct> list = tProductDao.queryByProductId(productId);
        return list;
    }

    // 通过状态和类型查询产品（使用状态和人群类型）
    @Override
    public List<VProduct> queryProductByStatusAndTypeName(int status, String typeName) {
        List<VProduct> list = tProductDao.queryProductByStatusAndTypeName(status, typeName);
        return list;
    }
}
