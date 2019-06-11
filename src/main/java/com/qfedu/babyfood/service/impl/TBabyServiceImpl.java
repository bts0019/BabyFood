package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.dao.TUserMapper;
import com.qfedu.babyfood.entity.TBaby;
import com.qfedu.babyfood.dao.TBabyMapper;
import com.qfedu.babyfood.entity.TUser;
import com.qfedu.babyfood.service.TBabyService;
import com.qfedu.babyfood.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Service
public class TBabyServiceImpl extends ServiceImpl<TBabyMapper, TBaby> implements TBabyService {

    @Autowired(required = false)
    private TBabyMapper tBabyMapper;

    @Override
    public R addTBaby(TBaby tBaby) {

        tBabyMapper.addTBaby(tBaby);
        return R.setOK("OK", null);
    }

    @Override
    public R queryAll() {

        return R.setOK("OK", tBabyMapper.queryAll());
    }

    @Override
    public R queryById(Integer babyId) {


        return R.setOK("OK", tBabyMapper.queryById(babyId));
    }

    @Override
    public R updateById(Integer babyId) {

        TBaby tBaby = tBabyMapper.queryById(babyId);
        tBaby.setPretty(tBaby.getPretty() + 1);
        tBabyMapper.updateByBabyId(tBaby);
        return R.setOK("OK", null);
    }
}
