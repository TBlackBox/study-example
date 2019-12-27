package cn.javafroum.mybatis.springboot2;

import cn.javafroum.mybatis.springboot2.controller.MybatisTest;
import cn.javafroum.mybatis.springboot2.mapper.LogMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot2ApplicationTests {

    @Test
    public void contextLoads() {

//        InputStream inp = new InputStream();
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inp);
//        SqlSession sqlSession =  sqlSessionFactory.openSession();
//        LogMapper mapper = sqlSession.getMapper(LogMapper.class);
//        mapper.selectLogList();
    }

}
