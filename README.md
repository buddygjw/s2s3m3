示例:Struts 2 & Spring 3 & MyBatis 3
====
声明：
    本示例程序是一个最简单的框架集成示例代码。
    用于简单了解Struts 2、Spring 3和MyBatis 3的集成方案，未任何的业务处理情况。
    本示例程序采用maven作为依赖管理工具，安装maven后，直接到项目中运行mvn jetty:run运行。
    同时，本程序是在s2s3的基础加上MyBatis来实现的。
    本程序同时还采用JUnit作为单元测试，用于测试Spring事务。
    运行时，请先修改jdbc.properties文件中的数据库连接信息。
    

1.加入依赖包，整个依赖树如下：

    +- org.mybatis:mybatis:jar:3.2.5
    +- org.mybatis:mybatis-spring:jar:1.2.2
    +- org.springframework:spring-tx:jar:3.0.5.RELEASE
    |  +- aopalliance:aopalliance:jar:1.0
    |  \- org.springframework:spring-aop:jar:3.0.5.RELEASE
    +- org.springframework:spring-jdbc:jar:3.0.5.RELEASE
    +- mysql:mysql-connector-java:jar:5.1.29

2.增加数据库表，用于测试：

    CREATE TABLE fk_user (
      ID int(11) NOT NULL AUTO_INCREMENT,
      NAME varchar(45) NOT NULL,
      GENDER tinyint(4) DEFAULT NULL,
      AGE int(11) DEFAULT NULL,
      PRIMARY KEY (ID)
    );



3.增加一个实体类，用于映射数据库中的FK_USER表，这个类只需要包含每个属性及其对应的setter/getter方法即可。详见：org.fkjava.example.s2s3m3.db.entity.User

4.增加数据访问接口（UserMapper），该接口只需要定义操作数据的方法就可以了。详见：org.fkjava.example.s2s3m3.db.dao.UserMapper

5.增加数据库表和对象的映射文件，该文件必须和数据访问接口同名，并且放在相同的路径中。
  在maven中，xml需要放到src/main/resources目录下，就可以自动编译到包中，所以我们需要在src/main/resources建立一个和UserMapper所在的包一样的目录结构。
  映射文件详见：org/fkjava/example/s2s3m3/db/dao/UserMapper.xml

7.修改applicationContext.xml文件，增加数据源和MyBatis的集成配置

    7.1.数据源和事务管理器
    
        <bean id="dataSource"
              class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
            <property name="driverClass" value="com.mysql.jdbc.Driver"></property> 
            <property name="url" value="jdbc:mysql://localhost:3306/test"></property>
            <property name="username" value="root"></property>
            <property name="password" value="root"></property>
        </bean>
        <bean id="transactionManager"
              class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dataSource" />
        </bean>

    7.2.增加Spring和MyBatis集成配置
    
        <!-- 配置SessionFactory和扫描实体类所在的包 -->
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="dataSource" ref="dataSource" />
            <!-- 自动扫描指定包下面的实体类别名 -->
            <property name="typeAliasesPackage" value="org.fkjava.example.s2s3m3.db.entity" /> 
        </bean>
        <!-- 扫描映射文件和接口所在的包 -->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <!-- 扫描Mapper接口 -->
            <property name="basePackage" value="org.fkjava.example.s2s3m3.db.dao" />
        </bean>

