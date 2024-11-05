<p align="center">
    <a href="https://github.com/Shingbb/leetmaster-backend" target="_blank">
      <img src="/doc/leetmaster_logo.png" width="280" alt="LeetMaster Logo"/>
    </a>
</p>

<h1 align="center">LeetMaster 面试大师</h1>
<p align="center">
  <strong>基于 Spring Boot + Redis + MySQL + Elasticsearch + Next.js</strong><br>
  一站式面试刷题平台，持续更新中...
</p>

<p align="center">
    <a href="https://github.com/Shingbb/leetmaster-backend"><img src="https://img.shields.io/badge/后端-项目地址-yellow.svg?style=plasticr"></a>
    <a href="https://github.com/Shingbb/leetmaster-frontend"><img src="https://img.shields.io/badge/前端-项目地址-blueviolet.svg?style=plasticr"></a>
</p>

---

## 项目简介

LeetMaster 面试大师是一个面试刷题平台，具有以下核心功能：
- **题库管理**：管理员可创建题库并批量关联题目
- **分词检索**：用户可以高效搜索题目
- **刷题功能**：用户可在线刷题并查看刷题记录日历

> 作者：[程序员Shing](https://github.com/Shingbb)

---

## 目录
- [快速启动](#快速启动)
- [技术栈](#技术栈)
- [项目配置](#项目配置)
    - [MySQL 数据库](#mysql-数据库)
    - [sa-token 分布式登录](#sa-token-分布式登录)

## 快速启动

在本地启动开发服务器：

```yml
# 克隆项目
git clone https://github.com/Shingbb/leetmaster-backend.git

# 进入项目目录
cd leetmaster-backend

# 启动项目
./mvnw spring-boot:run
```
## 技术栈
- **后端**：Spring Boot + Redis + MySQL + Elasticsearch + MyBatis-Plus + Swagger + sa-token
- **前端**：Next.js + Ant Design + React + TypeScript
- **测试**：JUnit + Mockito + JMeter + Selenium + Postman
- **部署**：Docker + Nginx

### 项目配置
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
