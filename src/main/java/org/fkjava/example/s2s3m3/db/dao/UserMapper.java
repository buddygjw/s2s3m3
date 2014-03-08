package org.fkjava.example.s2s3m3.db.dao;

import java.util.List;
import org.fkjava.example.s2s3m3.db.entity.User;

/**
 *
 * @author rodney
 */
public interface UserMapper {

    /**
     * 添加一个用户到数据库中
     *
     * @param user
     */
    void add(User user);

    public List<User> read();
}
