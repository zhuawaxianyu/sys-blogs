package com.doubao.sysblogs.sys.service;

import com.doubao.sysblogs.common.vo.Result;
import com.doubao.sysblogs.sys.entity.User;

import java.util.Map;

/**
 * @author doubao
 * @date 2024/07/15
 * @description 用户服务接口
 */
public interface UserService {
    /**
     * 登录
     * @param user 用户对象
     * @return 登录结果
     */
    Map<String, Object> login(User user);

    /**
     * 注册
     * @param user 用户对象
     * @return 注册结果
     */
    Result<?> register(User user);

    /**
     * 获取用户信息
     * @param token 登录令牌
     * @return 用户信息
     */
    Map<String, Object> getCurrentUserInfo(String token);
}
