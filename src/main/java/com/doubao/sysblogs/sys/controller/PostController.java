package com.doubao.sysblogs.sys.controller;


import com.doubao.sysblogs.common.utils.PageUtils;
import com.doubao.sysblogs.common.vo.Result;
import com.doubao.sysblogs.sys.entity.Posts;
import com.doubao.sysblogs.sys.service.PostService;
import com.doubao.sysblogs.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author doubao
 * @date 2024/07/15
 * @description 博客文章管理
 * 功能：
 *   - 创建新文章
 *   - 获取所有文章列表
 *   - 获取单篇文章详情
 *   - 更新文章
 *   - 删除文章
 */
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    /**
     * 根据用户的id获取该用户所有文章列表
     * @param userId 用户id
     * @param page 页码
     * @param size 每页数量
     * @param sort 排序方式，asc升序，desc降序
     *             默认为desc，即按创建时间降序排序
     * @return Result 包含文章列表和分页信息
     */
    @GetMapping
    public Result<?> getAllPostsByUser(
            @RequestParam(value = "uid") String userId,
            @RequestParam(value = "page") Long page,
            @RequestParam(value = "size") Long size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort) {
        List<Posts> posts = postService.getAllPostsByUser(userId, page, size, sort);
        PageUtils<Posts> postsPage = new PageUtils(size, page, posts);
        Map<String, Object> data = new HashMap<>();
        data.put("total", postsPage.getTotalCount());
        data.put("Records", postsPage.getList());
        return Result.success(data);
    }


    /**
     * 当前用户创建一个新文章
     * @param token 当前用户token
     * @param posts 文章对象
     * @return Result 成功或失败信息
     */
    @PostMapping("/add")
    public Result<Map<String, Object>> createPost(@RequestHeader("X-Token") String token, @RequestBody Posts posts) {
        String currentUserId = userService.getCurrentUserInfo(token).get("userId").toString();
        posts.setUserId(currentUserId);
        posts.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        Integer i = postService.createPost(posts);
        if (i > 0) {
            return Result.success("创建文章成功！");
        }
        return Result.fail("创建文章失败，请联系管理员！");
    }

    /**
     * 根据文章id获取文章详情
     * @param id 文章id
     * @return Result 包含文章详情的Result对象
     */
    @GetMapping("/{id}")
    public Result<Posts> getPostById(@PathVariable("id") String id) {
        Posts post = postService.getPostById(id);
        if (post != null) {
            return Result.success(post);
        }
        return Result.fail("该文章已不存在！");
    }

    /**
     * 根据文章id更新文章
     * @param token 当前用户token
     * @param id 文章id
     * @param posts 文章对象
     * @return Result 成功或失败信息
     */
    @PutMapping("/{id}")
    public Result<?> updatePost(@RequestHeader("X-Token") String token, @PathVariable("id") String id, @RequestBody Posts posts) {
        // 查出文章的原作者id
        Posts oldPost = postService.getPostById(id);
        if (oldPost != null) {
            String currentUserId = userService.getCurrentUserInfo(token).get("userId").toString();
            Integer i = postService.updatePost(currentUserId, oldPost.getUserId(), posts);
            if (i > 0) {
                return Result.success("更新文章成功！");
            } else if (i == -1) {
                return Result.fail("您没有权限修改该文章！");
            }
            return Result.fail("更新文章失败，请联系管理员！");
        }
        return Result.fail("文章不存在！");
    }

    /**
     * 根据文章id删除文章
     * @param token 当前用户token
     * @param id 文章id
     * @return Result 成功或失败信息
     */
    @DeleteMapping("/{id}")
    public Result<?> deletePost(@RequestHeader("X-Token") String token, @PathVariable("id") String id) {
        // 这里使用了物理删除，未使用逻辑删除
        Posts oldPost = postService.getPostById(id);
        if (oldPost != null) {
            String currentUserId = userService.getCurrentUserInfo(token).get("userId").toString();
            Integer i = postService.DeletePostById(currentUserId, oldPost);
            if (i > 0) {
                return Result.success("删除文章成功！");
            } else if (i == -1) {
                return Result.fail("您没有权限删除该文章！");
            }
            return Result.fail("删除文章失败，请联系管理员！");
        }
        return Result.fail("文章不存在！");
    }

}
