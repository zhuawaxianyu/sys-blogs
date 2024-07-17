package com.doubao.sysblogs.sys.mapper;

import com.doubao.sysblogs.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User selectByUsername(String username);

    /**
     * 添加用户
     * @param user 用户信息
     * @return 影响行数
     */
    Integer addUser(User user);
}
