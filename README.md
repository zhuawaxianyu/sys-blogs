### 博客系统API实现说明

#### 实现思路和注意事项

##### 技术栈选择
- **Java 17**: 使用最新的Java版本。
- **Spring Boot 3.x**: 提供快速的应用程序启动和自动配置。
- **MySQL**: 作为持久化存储数据库。
- **Mybatis**: 作为ORM框架，简化数据访问层。
- **JWT**: 用于实现基于token的身份验证机制。

##### 数据库设计
- **用户表 (`users`)**:
    - `user_id` (主键)
    - `username`
    - `password` (加密存储)
    - `email`
    - `created`
    - `last_modified`

- **文章表 (`posts`)**:
    - `post_id` (主键)
    - `title`
    - `content`
    - `user_id` (外键关联到 `users.user_id`)
    - `created`
    - `last_modified`

##### RESTful API设计
- **用户管理**:
    - `POST /api/auth/register`: 用户注册
    - `POST /api/auth/login`: 用户登录
    - `GET /api/auth/me`: 获取当前用户信息（需要认证）

- **博客文章管理**:
    - `POST /api/posts`: 创建新文章（需要登录）
    - `GET /api/posts?uid={user_id}&page={page}&size={size}&sort={sort}`: 获取某个用户的所有文章列表，支持分页和排序
    - `GET /api/posts/{id}`: 获取单篇文章详情
    - `PUT /api/posts/{id}`: 更新文章（需要登录和权限判断）
    - `DELETE /api/posts/{id}`: 删除文章（需要登录和权限判断）

##### 实现步骤和要点
1. **用户认证和授权**:
    - 使用JWT（JSON Web Token）生成和验证token，存储在请求的Authorization头部中。
    - 编写拦截器或过滤器来验证请求中的token的有效性，并提取当前用户信息。

2. **密码安全**:
    - 用户密码使用BCrypt等密码哈希方法进行加密存储，确保安全性。

3. **错误处理和日志记录**:
    - 使用全局异常处理器来处理API层面的异常，并记录错误日志以便追踪和调试。

4. **权限控制**:
    - 确保只有文章的作者可以进行更新和删除操作。在更新和删除接口中，检查当前用户是否是文章的作者。

5. **部署和Docker**:
    - 使用Docker容器化应用程序，并通过Docker Compose管理多个服务。
    - 编写Dockerfile和docker-compose.yml文件来定义应用程序的容器镜像和服务依赖关系。

#### 部署说明
- **Docker部署**:
    1. 编写`Dockerfile`来构建Java应用程序的镜像。
    2. 编写`docker-compose.yml`来定义应用程序、数据库等服务的配置。
    3. 使用`docker-compose up`命令启动应用程序。

