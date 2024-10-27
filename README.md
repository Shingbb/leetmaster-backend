# LeetMaster 面试大师

> 作者：[程序员Shing](https://github.com/Shingbb)

基于 Spring Boot + Redis + MySQL + Elasticsearch（+ Next.js 服务端渲染）的面试刷题平台。管理员可以创建题库并批量关联题目；用户可以分词检索题目、在线刷题并查看刷题记录日历等。




### MySQL 数据库

1）修改 `application.yml` 的数据库配置为你自己的：

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/my_db
    username: root
    password: 123456
```

2）执行 `sql/create_table.sql` 中的数据库语句，自动创建库表

3）启动项目，访问 `http://localhost:8101/api/doc.html` 即可打开接口文档，不需要写前端就能在线调试接口了~

![](doc/swagger.png)

### sa-token 分布式登录

1）修改 `application.yml` 的 Redis 配置为你自己的：

```yml
spring:
  redis:
    database: 1
    host: localhost
    port: 6379
    timeout: 5000
    password: 123456
```
2)登录请求头携带token名称（名称可自定义）以及值（通过登录接口的TokenLoginUserVo获取返回值）
```yml
############### Sa-Token 配置 (文档: https://sa-token.cc) ##############
sa-token:
  # token 名称（同时也是 cookie 名称）
  token-name: xxx-sa-token
```
