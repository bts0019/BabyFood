package com.qfedu.babyfood.service;

import com.qfedu.babyfood.dao.TBabyMapper;
import com.qfedu.babyfood.entity.TBaby;
import com.qfedu.babyfood.vo.R;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 查询个人宝宝及点赞信息
     * @param request
     * @return
     */
    public List<TBaby> queryBabyByUserId(String userName);
}
