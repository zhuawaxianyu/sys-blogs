package com.doubao.sysblogs.sys.controller;

import com.doubao.sysblogs.common.vo.Result;
import com.doubao.sysblogs.sys.entity.User;
import com.doubao.sysblogs.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author doubao
 * @date 2024/07/15
 * @description 用户管理
 * 功能：
 *  - 用户注册
 *  - 用户登录
 *  - 获取当前用户信息
 */
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) { // 为了能接收到这个json字符串，用这个@RequestBody注解
        Map<String, Object> data = userService.login(user);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20002, "用户名或密码错误！");
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 获取当前用户信息
     * @param token
     * @return
     */
    @GetMapping("/me")
    public Result<Map<String, Object>> getCurrentUserInfo(@RequestParam("token") String token) {
        Map<String, Object> currentUserInfo = userService.getCurrentUserInfo(token);
        if (currentUserInfo != null) {
            return Result.success(currentUserInfo);
        }
        return Result.fail(20003, "当前用户登录无效！");
    }
}
