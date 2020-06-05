# SSM整合

通过一个 书籍管理系统 来整合MyBatis, Spring和SpringMVC三个框架

需要实现的功能:

1. 用户登录/注销
2. 书籍的增加/修改/删除/查询

![书籍管理系统](D:\Desktop\学习笔记\resources\书籍管理系统.jpg)

建议的编写顺序: 自底向上, 持久层 -> 业务层 -> 表现层 -> 前端

需要的依赖: 数据库驱动, 数据库连接池, mybatis, mybatis-spring, spring相关, servlet, jsp, 其他工具(junit, lombok, aspectjwaver等)

```xml
<dependencies>
    <!--数据库驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.20</version>
    </dependency>
    <!--数据库连接池-->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.22</version>
    </dependency>
    <!--servlet jsp-->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.2</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <!--mybatis-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.4</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.4</version>
    </dependency>
    <!--spring-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.2.6.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.2.6.RELEASE</version>
    </dependency>
    <!--其他工具-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>5.2.6.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.5</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

配置静态资源导出, 提高运行效率

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

## 1. 持久层

涉及的类和配置文件: 

1. pojo类: Book, User
2. Dao接口: BookMapper, UserMapper以及对应的配置文件
3. Mybatis配置文件: mybatis-config.xml
4. spring配置: spring-dao.xml

### 1.1 编写pojo类

```java
@Data
public class Book {
    private int bookId;
    private String bookName;
    private int bookCount;
    private String detail;
}
```

```java
@Data
public class User {
    private int userId;
    private String username;
    private String password;
}
```

### 1.2 编写dao接口和对应Mapper配置

```java
public interface BookMapper {

    // 查询所有书籍
    List<Book> findAllBook();

    // 根据书名模糊查询
    Book findBookByName(String bookName);

    // 根据ID查询
    Book findBookById(int bookId);

    // 添加书籍
    int addBook(Book book);

    // 移除书籍
    int deleteBook(int bookId);

    // 更新书籍信息
    int updateBook(Book book);
}
```

```java
public interface UserMapper {

    // 根据username查询用户
    User findUserByName(String username);
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.myssm.dao.BookMapper">
    <select id="findAllBook" resultType="Book">
        select * from book
    </select>
    
    <select id="findBookByName" resultType="Book">
        select * from book where bookName like #{bookName}
    </select>

    <select id="findBookById" resultType="Book">
        select * from book where bookId=#{bookId}
    </select>

    <insert id="addBook" parameterType="Book">
        insert into book(bookName, bookCount, detail) values(#{bookName}, #{bookCount}, #{detail})
    </insert>

    <update id="updateBook" parameterType="Book">
        update book set bookName=#{bookName}, bookCount=#{bookCount}, detail=#{detail} where bookId=#{bookId}
    </update>

    <delete id="deleteBook">
        delete from book where bookId=#{bookId}
    </delete>
</mapper>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper>
    <select id="findUserByName" resultType="User" parameterType="String">
        select * from user where username=#{username}
    </select>
</mapper>
```

### 1.3 配置mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    <!--输出log-->
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>

    <typeAliases>
        <package name="com.myssm.pojo"/>
    </typeAliases>

    <mappers>
        <package name="com.myssm.dao"/>
    </mappers>

</configuration>
```

### 1.4 配置spring-dao.xml

把数据库的连接配置放到spring中管理

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--关联数据库配置文件-->
    <context:property-placeholder location="database.properties"/>

    <!--配置数据库连接池-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!--配置SqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>

    <!--配置dao接口扫描包, 动态实现了Dao接口可以注入到spring容器中-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" id="mapperScannerConfigurer">
        <!--注入sqlSessionFactory-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!--需要扫描的Dao包-->
        <property name="basePackage" value="com.ssm.dao"/>
    </bean>

</beans>
```



配置完上面的内容, 我们可以先进行一下测试, 保证dao层的配置没有问题. 检查无误后, 开始编写Service层

## 2. 业务层

业务层的工作主要包括: 对持久层方法的调用, AOP配置

涉及的类和配置文件:

1. BookService, UserService及其实现类
2. spring-service.xml

### 2.1 编写Service接口和实现类

这里使用注解的是方式配置Service的实现类, 可以注意到这里由于没有配置其他功能, 所有业务层接口与持久层接口内容相同

```java
public interface BookService {

    // 查询所有书籍
    List<Book> findAllBook();

    // 根据书名模糊查询
    List<Book> findBookByName(String bookName);

    // 根据ID查询
    Book findBookById(int bookId);

    // 添加书籍
    int addBook(Book book);

    // 移除书籍
    int deleteBook(int bookId);

    // 更新书籍信息
    int updateBook(Book book);
}
```

```java
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAllBook() {
        return bookMapper.findAllBook();
    }

    @Override
    public List<Book> findBookByName(String bookName) {
        return bookMapper.findBookByName(bookName);
    }

    @Override
    public Book findBookById(int bookId) {
        return bookMapper.findBookById(bookId);
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBook(int bookId) {
        return bookMapper.deleteBook(bookId);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }
}
```

```java
public interface UserService {
    // 根据username查询用户
    User findUserByName(String username);
}
```

```java
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
```

### 2.2 编写spring-service.xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--使用注解的方式需要配置spring扫描的包-->
    <context:component-scan base-package="com.myssm.service"/>

    <!--声明式事务配置-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--注入数据源-->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--aop事务支持-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
    <aop:config>
        <aop:pointcut id="txPointCut" expression="execution(* com.myssm.dao.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>

</beans>
```



同样, 业务层配置完后, 可以先测试一下, 保证业务层代码没有问题. 接下来配置MVC层, 配置MVC层时, 可以和前端同时进行

## 3 配置表现层和前端

我们希望完成的工作有:

1. 登录/注销
2. 书籍管理

对于登录注销可以使用springmvc中的拦截器实现, 书籍管理通过Controller调用业务层的方法实现

涉及的配置:

1. web.xml
2. spring-mvc.xml
3. 前端页面
4. Controller类

### 3.1 配置web.xml

在web.xml中, 我们只需要配置servlet和filter, 使用前端控制器DispatcherServlet让表现层由spring接管

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <session-config>
        <session-timeout>10</session-timeout>
    </session-config>
</web-app>
```

### 3.2 配置spring-mvc.xml

需要配置的有: 处理器映射器/处理器适配器(这两个只需要开启事务支持就能默认加载), 视图解析器, 拦截器等

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--配置徐要扫描的包-->
    <context:component-scan base-package="com.myssm.controller"/>

    <!--静态资源过滤-->
    <mvc:default-servlet-handler/>

    <!--开启注解支持-->
    <mvc:annotation-driven/>

    <!--配置视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <!--只需要配置前缀和后缀-->
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--拦截器配置-->
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/user/**"/>
            <bean class="com.myssm.config.LoginInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>

</beans>
```

### 3.3 编写前端页面

可以使用bootstrap的可视化工具创建

[查看前端源码](https://github.com/tangjiacheng/ssm_01book/tree/master/web)

### 3.4 拦截器配置

在用户未登录时, 执行的所有请求都会被转发到登录页面, 因此需要springMVC中自带的拦截器来实现. 拦截器的底层还是基于AOP的实现

首先创建一个类实现Interceptor, 重写preHandle方法并定义拦截

```java
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Interceptor ==> ");

        HttpSession session = request.getSession();

        if (request.getRequestURI().contains("toLogin")) {
            return true;
        }

        if (request.getRequestURI().contains("login")) {
            return true;
        }

        if (session.getAttribute("userLoginInfo") != null) {
            return true;
        }

        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        return false;
    }
}
```

然后在spring-mvc.xml中添加拦截器配置, 拦截/book/下的所有页面

```xml
<!--拦截器配置-->
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/book/**"/>
        <bean class="com.myssm.config.LoginInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```

### 3.5 编写Controller类, 映射前端请求

采用注解的方式, 在类上面加@Controller注解, 在方法上添加@RequestMapping注解

```java
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(HttpSession session) {
        if (session.getAttribute("userLoginInfo") != null) {
            return "redirect:/book/main";
        }
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password, Model model) {
        System.out.println("login ==> " + username + ", " + password);
        User user = userService.findUserByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }else {
            session.setAttribute("userLoginInfo", username);
            return "redirect:/book/main";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        // 移除session中的用户信息
        session.removeAttribute("userLoginInfo");
        return "login";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        List<Book> books = bookService.findAllBook();
        model.addAttribute("books", books);
        return "main";
    }

    //跳转到添加书籍页面
    @RequestMapping("/toAdd")
    public String toAddPage() {
        return "addBook";
    }

    //添加书籍
    @RequestMapping("/addBook")
    public String addBook(Book book) {
        bookService.addBook(book);
        return "redirect:/book/allBook"; // 重定向
    }

    //跳转到修改页面
    @RequestMapping("/toUpdate")
    public String toUpdatePage(int bookId, Model model) {
        Book book = bookService.findBookById(bookId);
        model.addAttribute("QBook", book);
//        System.out.println("toUpdate == > " + book);
        return "updateBook";
    }

    //修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Book book) {
        bookService.updateBook(book);
        return "redirect:/book/main";
    }

    //删除书籍
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/book/main";
    }

    //查询书籍
    @RequestMapping("/findBook")
    public String queryBook(String queryBookName, Model model) {
        List<Book> books = bookService.findBookByName("%" + queryBookName + "%");
        if (books.isEmpty()) {
            model.addAttribute("error", "未查询到 " + queryBookName);
        }
        model.addAttribute("books", books);
        return "main";
    }
}
```

## 4 配置Tomcat

下载tomcat服务器, 在idea中添加配置, 运行即可

[下载tomcat](https://tomcat.apache.org/download-90.cgi)

## 遇到的问题

### 1. org.springframework.beans.factory.NoSuchBeanDefinitionException

把扫描包的代码

```xml
<context:component-scan base-package="com.myssm.controller"/>
<context:component-scan base-package="com.myssm.service.impl"/>
```

放到applicationContext.xml文件中

(这个问题我后来发现好像是因为web.xml配置文件中的contextConfigLocation的值设置成了spring-mvc.xml, 改回来之后应该即使不做上面的调整也能正常运行)

## 2. jar包缺失

1. 检查是否正常导入maven坐标
2. 检查idea中: project structure -> project setting -> artifacts 中, WEB-INF文件夹下是否存在lib文件夹, 如果没有需要创建一个, 并将工程中的jar包都放进去