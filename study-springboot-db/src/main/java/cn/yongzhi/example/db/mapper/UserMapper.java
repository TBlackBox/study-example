package cn.yongzhi.example.db.mapper;

import cn.yongzhi.example.db.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @Author yongzhi
 * @Date 2021/3/10 14:43
 **/

@Repository
public interface UserMapper {

    void insertUser(User user);

    User selectById(Integer id);
}
