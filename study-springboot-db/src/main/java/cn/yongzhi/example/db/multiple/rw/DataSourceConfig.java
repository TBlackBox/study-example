package cn.yongzhi.example.db.multiple.rw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yongzhi
 * @Date 2021/3/10 13:50
 **/
@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);


    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource")
//    @ConfigurationProperties(prefix = "druid.datasource")
    public DataSource masterDataSource(){
        log.info("创建master 数据源");
        //使用默认的数据源类型
//        return DataSourceBuilder.create().build();
        //使用druid 数据源
        return DataSourceBuilder.create().type(dataSourceType).build();
    }


    @Bean("slave")
    @ConfigurationProperties(prefix = "druid.slave")
    public DataSource slaveDataSource(){
        log.info("创建 slave 数据源");
        return DataSourceBuilder.create().build();
    }

    @Bean("myRoutingDataSource")
    @Primary //当有多个相同类型的Bean时，优先使用@Primary注解的Bean
    DataSource primaryDataSource(
            @Autowired @Qualifier("master") DataSource masterDataSource,
            @Autowired @Qualifier("slave") DataSource slaveDataSource
    ) {
        log.info("创建数据源路由。。。。");
        Map<Object, Object> map = new HashMap<>();
        map.put(DataSourceEnum.MASTER.name(), masterDataSource);
        map.put(DataSourceEnum.SLAVE.name(), slaveDataSource);

        RoutingDataSource routing = new RoutingDataSource();
        routing.setTargetDataSources(map);
        //设置默认的数据源  即主数据源
        routing.setDefaultTargetDataSource(masterDataSource);
        return routing;
    }



}
