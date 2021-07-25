package cn.yongzhi.example.db.service;

import cn.yongzhi.example.db.domain.User;
import cn.yongzhi.example.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author yongzhi
 * @Date 2021/3/10 14:42
 **/
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Transactional(rollbackFor = Throwable.class)
    public void save(User user){
        userMapper.insertUser(user);
    }

    @Transactional(readOnly = true)
    public User selectById(Integer id){
        return userMapper.selectById(id);
    }


}
