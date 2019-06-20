package com.qfedu.babyfood.dao;

import com.qfedu.babyfood.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jesion
 * @since 2019-06-06
 */
public interface TUserMapper extends BaseMapper<TUser> {

    /**
     * 通过用户id查询用户的信息
     * @param userId 用户id
     * @return
     */
    TUser selectByUserId(Integer userId);

    TUser selectByemail(String email);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    TUser selectByName(String username);

    /**
     * 根据宝宝id查找点赞的时间
     * @param babyId
     * @return
     */
    TUser selectByBabyId(Integer babyId);

    /**
     * 修改用户点赞的时间
     * @param tUser
     */
    void updateFlagTime(TUser tUser);
}
