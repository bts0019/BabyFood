package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TBaby;
import com.baomidou.mybatisplus.service.IService;
import com.qfedu.babyfood.vo.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TBabyService extends IService<TBaby> {

    public R addTBaby(TBaby tBaby);

    R queryAll();

    R queryById(Integer id);

    R updateById(Integer id);
}
