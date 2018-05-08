package com.example.base.dao;

import com.example.base.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Description: User对象Dao
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/4/24
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    static final String GET_USER_BY_USERNAME = "from User u where u.userName = ?";

    static final String QUERY_USER_BY_USERNAME = "from User u where u.userName like ?";


}
