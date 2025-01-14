## 技术栈版本

### Java 版本
- **JDK 版本**：17
    - 项目使用了 Java 17 作为开发语言，确保代码兼容性和性能优化。
    - 在 `build.gradle` 中通过 `sourceCompatibility = '17'` 指定了 Java 版本。

### Gradle 版本
- **Gradle 版本**：8.5
    - 项目使用 Gradle 8.5 作为构建工具，管理依赖和构建流程。
    - 在 `gradle-wrapper.properties` 中通过 `distributionUrl` 指定了 Gradle 版本：
      ```properties
      distributionUrl=https\://services.gradle.org/distributions/gradle-8.5-bin.zip
      ```
# 1. 项目结构概览
```angular2html
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── demo/
│   │               ├── DemoApplication.java         # 项目启动入口
│   │               ├── controller/                  # 控制器：处理 HTTP 请求
│   │               │   ├── HelloController.java
│   │               │   ├── TestController.java
│   │               │   └── UserController.java
│   │               ├── entity/                      # 实体类：映射数据库表
│   │               │   └── User.java
│   │               ├── repository/                  # 数据仓库：操作数据库
│   │               │   └── UserRepository.java
│   │               └── service/                     # 服务层：处理业务逻辑
│   │                   └── UserService.java
│   └── resources/                                   # 资源文件
│       ├── application.properties                   # 配置文件
│       ├── log4j2-spring.xml                        # 日志配置
│       ├── static/                                  # 静态资源（如 HTML）
│       │   └── index.html
│       └── templates/                               # 模板文件（如 Thymeleaf）
└── test/                                            # 测试代码
```
# 2. 数据流向详解
```angular2html
2.1 用户访问网页（index.html）
文件：src/main/resources/static/index.html

作用：这是一个简单的 HTML 页面，用户通过浏览器访问时，会直接显示这个页面。

数据流向：

用户访问 http://localhost:8080/，Spring Boot 会自动返回 index.html 页面。

2.2 用户发起 HTTP 请求（HelloController）
文件：src/main/java/com/example/demo/controller/HelloController.java

作用：处理用户发起的 HTTP 请求，返回简单的字符串响应。

数据流向：

用户访问 http://localhost:8080/hello，HelloController 的 sayHello() 方法会被调用。

方法返回字符串 "Hello, 你在干嘛!"，浏览器显示这个字符串。

2.3 用户查询数据（UserController -> UserService -> UserRepository）
文件：

src/main/java/com/example/demo/controller/UserController.java

src/main/java/com/example/demo/service/UserService.java

src/main/java/com/example/demo/repository/UserRepository.java

作用：处理用户相关的请求，比如添加用户或查询用户。

数据流向：

用户通过 HTTP 请求访问 http://localhost:8080/api/users/query?username=xxx。

UserController 接收到请求，调用 UserService 的 getUserByUsername() 方法。

UserService 调用 UserRepository 的 findByUsername() 方法，从数据库中查询用户数据。

查询结果返回给 UserService，再返回给 UserController，最后返回给用户。

2.4 数据库操作（UserRepository -> 数据库）
文件：src/main/java/com/example/demo/repository/UserRepository.java

作用：通过 Spring Data JPA 操作数据库。

数据流向：

UserRepository 继承 JpaRepository，提供了基本的 CRUD 操作。

当 UserService 调用 findByUsername() 时，UserRepository 会自动生成 SQL 查询，从数据库中获取数据。

2.5 日志记录（log4j2-spring.xml）
文件：src/main/resources/log4j2-spring.xml

作用：配置日志输出格式和级别。

数据流向：

当控制器或服务层的方法被调用时，日志信息会被记录到控制台或文件中。

例如，HelloController 中的 logger.info("Request to /hello endpoint") 会输出一条日志。
```
# 3. 一步步实现这个效果
```angular2html
3.1 第一步：创建 Spring Boot 项目
使用 IntelliJ IDEA 创建一个 Spring Boot 项目，选择 Gradle 作为构建工具。

项目创建完成后，会自动生成 DemoApplication.java 和 build.gradle。

3.2 第二步：添加控制器
创建 HelloController.java，编写一个简单的 @RestController，处理 /hello 请求。

启动项目，访问 http://localhost:8080/hello，查看返回结果。

3.3 第三步：添加实体类和服务层
创建 User.java 实体类，定义用户的基本属性（如 id、username、password）。

创建 UserRepository.java，继承 JpaRepository，提供数据库操作方法。

创建 UserService.java，编写业务逻辑（如添加用户、查询用户）。

3.4 第四步：添加控制器处理用户请求
创建 UserController.java，编写 @PostMapping 方法，处理用户添加和查询请求。

启动项目，使用 Postman 或浏览器测试 /api/users/add 和 /api/users/query 接口。

3.5 第五步：配置日志
创建 log4j2-spring.xml，配置日志输出格式和级别。

在控制器和服务层中添加日志记录代码，查看日志输出。

3.6 第六步：添加静态页面
在 src/main/resources/static/ 下创建 index.html，编写一个简单的 HTML 页面。

启动项目，访问 http://localhost:8080/，查看页面内容。
```

# 4. 效果图
访问地址：http://localhost:8080/
![20250114103240](https://liu-fu-gui.github.io/myimg/halo/20250114103240.png)
访问地址：http://localhost:8080/swagger-ui/index.html#/
![20250114102853](https://liu-fu-gui.github.io/myimg/halo/20250114102853.png)