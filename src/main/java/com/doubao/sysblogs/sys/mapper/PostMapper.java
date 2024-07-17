package com.doubao.sysblogs.sys.mapper;

import com.doubao.sysblogs.sys.entity.Posts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    /**
     * 根据用户ID查询所有文章
     * @param userId 用户ID
     * @return 文章列表
     */
    List<Posts> selectAllPostsByUser(String userId);

    /**
     * 插入文章
     * @param posts 文章对象
     * @return 影响行数
     */
    Integer insertPost(Posts posts);

    /**
     * 根据ID查询文章
     * @param id 文章ID
     * @return 文章对象
     */
    Posts selectPostById(String id);

    /**
     * 更新文章
     * @param posts
     * @return
     */
    Integer updatePost(Posts posts);

    /**
     * 根据ID删除文章
     * @param id 文章ID
     * @return 影响行数
     */
    Integer deletePostById(Integer id);
}
