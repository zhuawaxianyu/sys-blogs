package com.doubao.sysblogs.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author doubao
 * @date 2024/07/15
 * 博客文章实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Posts implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 作者ID，关联到用户表
     */
    private String userId;

    /**
     * 创建时间
     */
    private Timestamp created;

    /**
     * 最后修改时间
     */
    private Timestamp lastModified;
}
