package com.doubao.sysblogs.sys.service.impl;

import com.doubao.sysblogs.common.utils.JwtUtil;
import com.doubao.sysblogs.common.vo.Result;
import com.doubao.sysblogs.sys.entity.User;
import com.doubao.sysblogs.sys.mapper.UserMapper;
import com.doubao.sysblogs.sys.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(User user) {
        User loginUser = userMapper.selectByUsername(user.getUsername());
        if(user != null || user.getPassword().equals(user.getPassword())) {
            String token = jwtUtil.createToken(loginUser);
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(token, token, 30, TimeUnit.MINUTES);
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return data;
        }
        return null;
    }

    @Override
    public Result<?> register(User user) {
        User loginUser = userMapper.selectByUsername(user.getUsername());
        // 注册账号已存在
        if(loginUser != null) {
            return Result.fail("用户名已存在");
        }
        // 将新增的密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        Integer i = userMapper.addUser(user);
        if(i > 0) {
            return Result.success("用户注册成功！");
        }
        return Result.fail("用户注册失败！");
    }

    @Override
    public Map<String, Object> getCurrentUserInfo(String token) {
        // 根据token获取用户信息
        String obj = (String) redisTemplate.opsForValue().get(token);
        if (obj != null) {
            User loginUser = null;
            try {
                loginUser = jwtUtil.parseToken(obj, User.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (loginUser != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("userId", loginUser.getId());
                data.put("name", loginUser.getUsername());
                data.put("email", loginUser.getEmail());
                return data;
            }
        }
        return null;
    }
}
