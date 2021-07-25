package cn.yongzhi.example.db.multiple.rw;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author yongzhi
 * @Date 2021/3/11 12:03
 **/
@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);



    @Around("@annotation(cn.yongzhi.example.db.multiple.rw.DataSourceSwitcher)")
//1
//    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object setDynamicDataSource(ProceedingJoinPoint pjp) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            DataSourceSwitcher dataSourceSwitcher = signature.getMethod().getAnnotation(DataSourceSwitcher.class);

            if(dataSourceSwitcher.value() != DataSourceEnum.MASTER){
                RoutingDataSourceContext.setDataSourceKey(dataSourceSwitcher.value());
                log.info("数据源切换至：" + dataSourceSwitcher.value().name());
            }
//2
//            Transactional transactional =signature.getMethod().getAnnotation(Transactional.class);
//            if(transactional.readOnly()){
//                RoutingDataSourceContext.setDataSourceKey(DataSourceEnum.SLAVE);
//                log.info("数据源切换至只读数据库");
//            }

            return pjp.proceed();
        } finally {
                RoutingDataSourceContext.remove();
        }
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
