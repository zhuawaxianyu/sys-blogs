package com.doubao.sysblogs.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author doubao
 * @date 2024/07/15
 * 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     *  创建时间
     */
    private Timestamp created;

    /**
     *  更新时间
     */
    private Timestamp lastModified;
}
