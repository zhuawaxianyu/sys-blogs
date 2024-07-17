# 使用官方的 OpenJDK 11 镜像作为基础镜像
FROM openjdk:17-jdk-slim
# 设置工作目录
WORKDIR /app
# 将本地的 JAR 文件复制到 Docker 镜像中的工作目录
COPY target/sys-blogs-0.0.1-SNAPSHOT.jar /app/sys-blogs-0.0.1-SNAPSHOT.jar
# 暴露应用程序的端口号（假设 Spring Boot 应用程序使用的是默认的 8080 端口）
EXPOSE 9999
# 定义容器启动时运行的命令，启动 Spring Boot 应用程序
CMD ["java", "-jar", "sys-blogs-0.0.1-SNAPSHOT.jar"]
