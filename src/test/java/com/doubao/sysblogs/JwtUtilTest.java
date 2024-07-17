package com.doubao.sysblogs;

import com.doubao.sysblogs.common.utils.JwtUtil;
import com.doubao.sysblogs.sys.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    // 测试Jwt生成token
    @Test
    public void testCreateJwt() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        String token = jwtUtil.createToken(user);
        System.out.println(token);
    }

    // 测试token解析回user对象
    @Test
    public void TestParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxYzY3YmMzNS04MTUwLTRlMDAtOWRhMi1lOTYxMTdjMDljZGMiLCJzdWIiOiJ7XCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJ1c2VybmFtZVwiOlwiemhhbmdzYW5cIn0iLCJpc3MiOiJzeXN0ZW0iLCJpYXQiOjE3MjEwNjk2NDEsImV4cCI6MTcyMTA3MTQ0MX0._j1XKJ0yCQg11l7RNs-iv5OHxHL6t-LbfeaamMfGOms";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }


    @Test
    public void TestParseJwt2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxYzY3YmMzNS04MTUwLTRlMDAtOWRhMi1lOTYxMTdjMDljZGMiLCJzdWIiOiJ7XCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJ1c2VybmFtZVwiOlwiemhhbmdzYW5cIn0iLCJpc3MiOiJzeXN0ZW0iLCJpYXQiOjE3MjEwNjk2NDEsImV4cCI6MTcyMTA3MTQ0MX0._j1XKJ0yCQg11l7RNs-iv5OHxHL6t-LbfeaamMfGOms";
        User user = jwtUtil.parseToken(token, User.class);
        System.out.println(user);
    }


}
