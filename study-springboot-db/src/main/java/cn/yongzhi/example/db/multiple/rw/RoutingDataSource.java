package cn.yongzhi.example.db.multiple.rw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author yongzhi
 * @Date 2021/3/10 14:05
 **/
public class RoutingDataSource extends AbstractRoutingDataSource {

    static final Logger log = LoggerFactory.getLogger(RoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String key = RoutingDataSourceContext.getDataSourcesKey();
        log.info("当前数据库key：" + key);
        return key;
    }
}
