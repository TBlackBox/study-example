package cn.yongzhi.example.db.multiple.rw;

/**
 * @Author yongzhi
 * @Date 2021/3/10 14:12
 **/
public class RoutingDataSourceContext implements AutoCloseable {

    static final ThreadLocal<DataSourceEnum> threadLocalDataSourceKey = new ThreadLocal<>();

    public static String getDataSourcesKey(){
        DataSourceEnum key = threadLocalDataSourceKey.get();
        return key == null ? DataSourceEnum.MASTER.name():key.name();
    }

    public static void setDataSourceKey(DataSourceEnum dateSourceEnum){
        threadLocalDataSourceKey.set(dateSourceEnum);
    }

    public RoutingDataSourceContext(DataSourceEnum key){
        this.threadLocalDataSourceKey.set(key);
    }


    public static void remove(){
        threadLocalDataSourceKey.remove();
    }



    @Override
    public void close() throws Exception {
        threadLocalDataSourceKey.remove();
    }
}
