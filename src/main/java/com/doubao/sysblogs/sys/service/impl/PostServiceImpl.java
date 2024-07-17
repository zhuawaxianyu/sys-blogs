package com.doubao.sysblogs.sys.service.impl;

import com.doubao.sysblogs.common.utils.StringUtil;
import com.doubao.sysblogs.sys.entity.Posts;
import com.doubao.sysblogs.sys.mapper.PostMapper;
import com.doubao.sysblogs.sys.service.PostService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postsMapper;

    @Override
    public List<Posts> getAllPostsByUser(String userId, Long page, Long size, String sort) {
        if (StringUtil.isNotEmpty(userId)) {
            List<Posts> postList = postsMapper.selectAllPostsByUser(userId);
            List<Posts> sortedPostList = postList.stream()
                    .sorted((p1, p2) -> {
                        if (sort.equalsIgnoreCase("asc")) {
                            return p1.getCreated().compareTo(p2.getCreated());
                        } else {
                            return p2.getCreated().compareTo(p1.getCreated());
                        }
                    })
                    .collect(Collectors.toList());
            return sortedPostList;
        }
        return new ArrayList<>();
    }

    @Override
    public Integer createPost(Posts posts) {
        return postsMapper.insertPost(posts);
    }

    @Override
    public Posts getPostById(String id) {
        return postsMapper.selectPostById(id);
    }

    @Override
    public Integer updatePost(String currentUserId, String PostUserId, Posts posts) {
        if (currentUserId != null && currentUserId.equals(PostUserId)) {
            posts.setLastModified(Timestamp.valueOf(LocalDateTime.now()));
            return postsMapper.updatePost(posts);
        }
        return -1;
    }

    @Override
    public Integer DeletePostById(String currentUserId, Posts post) {
        if (currentUserId != null && currentUserId.equals(post.getUserId())) {
            return postsMapper.deletePostById(post.getId());
        }
        return -1;
    }

}
