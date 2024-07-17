package com.doubao.sysblogs.sys.service;

import com.doubao.sysblogs.sys.entity.Posts;

import java.util.List;

/**
 * @author doubao
 * @date 2024/07/15
 * @description 博客文章管理服务接口
 */
public interface PostService {
    /**
     * 根据用户ID获取该用户所有文章
     * @param userId
     * @param page
     * @param size
     * @param sort
     * @return
     */
    List<Posts> getAllPostsByUser(String userId, Long page, Long size, String sort);

    /**
     * 当前用户创建文章
     * @param posts
     * @return
     */
    Integer createPost(Posts posts);

    /**
     * 根据文章ID获取文章详情
     * @param id
     * @return
     */
    Posts getPostById(String id);

    /**
     * 权限判断是否可以更新文章
     * @param currentUserId
     * @param PostUserId
     * @param posts
     * @return
     */
    Integer updatePost(String currentUserId, String PostUserId, Posts posts);

    /**
     * 权限判断是否可以根据文章ID删除文章
     * @param currentUserId
     * @param post
     * @return
     */
    Integer DeletePostById(String currentUserId, Posts post);
}
