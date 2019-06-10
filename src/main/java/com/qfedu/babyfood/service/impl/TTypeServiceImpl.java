package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.entity.TType;
import com.qfedu.babyfood.dao.TTypeMapper;
import com.qfedu.babyfood.service.TTypeService;
import com.qfedu.babyfood.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Service
public class TTypeServiceImpl extends ServiceImpl<TTypeMapper, TType> implements TTypeService {

    @Autowired(required = false)
    private TTypeMapper tTypeMapper;

    @Override
    public R queryAll() {
        return R.setOK("OK", tTypeMapper.queryAll());
    }
}
