package cn.yongzhi.example.db.multiple.rw;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Author yongzhi
 * @Date 2021/3/11 11:46
 **/
@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("myRoutingDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        /*mybaits 配置文件*/
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.yongzhi.example.db.domain");
        Resource configResource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(configResource);

        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("myRoutingDataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
