package com.qfedu.babyfood.service;

import com.qfedu.babyfood.entity.TKnowledge;
import com.baomidou.mybatisplus.service.IService;
import com.qfedu.babyfood.vo.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
public interface TKnowledgeService extends IService<TKnowledge> {

    R queryAll();
}
