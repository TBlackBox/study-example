package cn.yongzhi.example.db;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Repository;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"cn.yongzhi.example.db.mapper"},annotationClass = Repository.class)
public class StudySpringbootDbApplication {

    static final Logger log = LoggerFactory.getLogger(StudySpringbootDbApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudySpringbootDbApplication.class, args);
    }

}
