package org.fkjava.example.s2s3m3.service;

import javax.annotation.Resource;
import org.fkjava.example.s2s3m3.db.dao.UserMapper;
import org.fkjava.example.s2s3m3.db.entity.Gender;
import org.fkjava.example.s2s3m3.db.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rodney
 */
@Service()
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 测试事务的服务方法
     */
    @Transactional
    public void test() {
        User user = new User();
        user.setName("jack");
        user.setAge(12);
        user.setGender(Gender.MALE);

        userMapper.add(user);

        user = new User();
        user.setName(null);
        user.setAge(12);
        user.setGender(Gender.MALE);

        userMapper.add(user);
    }
}
