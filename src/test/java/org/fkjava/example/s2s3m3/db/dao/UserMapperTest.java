/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fkjava.example.s2s3m3.db.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fkjava.example.s2s3m3.db.entity.Gender;
import org.fkjava.example.s2s3m3.db.entity.User;
import org.fkjava.example.s2s3m3.service.UserService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rodney
 */
public class UserMapperTest {

    private static ApplicationContext ctx;
    private SqlSession session;

    public UserMapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        //类加载进来的时候，就初始化好Spring
        ctx = new ClassPathXmlApplicationContext(new String[]{"/applicationContext.xml"});
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //每次运行测试方法的时候，都获取一个Session
        SqlSessionFactory sqlSessionFactory = ctx.getBean(SqlSessionFactory.class);
        this.session = sqlSessionFactory.openSession();
    }

    @After
    public void tearDown() {
        //每次完成一个测试方法的时候，都关闭session，但是不负责提交事务。事务由测试方法进行提交。
        this.session.close();
    }

    /**
     * Test of add method, of class UserMapper.
     */
    //@Test
    public void testAdd() {
        System.out.println("add");
        User user = new User();
        user.setName("jack");
        user.setAge(12);
        user.setGender(Gender.MALE);
        UserMapper instance = this.session.getMapper(UserMapper.class);
        instance.add(user);
        this.session.commit();
    }

    @Test
    public void testRead() {
        System.out.println("read");
        UserMapper instance = this.session.getMapper(UserMapper.class);
        List<User> list = instance.read();
        for (User user : list) {
            System.out.println(user.getName() + ", " + user.getGender() + "," + user.getAge());
        }
    }

    @Test
    public void testTx() {
        System.out.println("tx");
        UserMapper instance = this.session.getMapper(UserMapper.class);
        List<User> list = instance.read();
        try {
            UserService userService = ctx.getBean(UserService.class);
            userService.test();
        } catch (Exception ex) {
        }
        List<User> list2 = instance.read();
        Assert.assertEquals("添加前和添加后，数据库记录发生了变化", list.size(), list2.size());
    }
}
