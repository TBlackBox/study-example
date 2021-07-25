package cn.yongzhi.example.db.multiple.rw;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSwitcher {

    /**
     * 默认数据源 主数据源
     */
    DataSourceEnum value() default DataSourceEnum.MASTER;

}
