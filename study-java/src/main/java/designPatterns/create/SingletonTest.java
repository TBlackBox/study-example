package designPatterns.create;

/**
 * 单列设计模式
 * @Author yongzhi
 * @Date 2021/3/17 19:16
 **/
public class SingletonTest {

    public static void main(String[] args) {

        //获取实列
        LazyConfigContext.getInstance();

        //获取饿汉式实列
        ConfigContext.getInstance();

    }
}


//懒汉式
class LazyConfigContext{

    private static LazyConfigContext instance = null;

    public static LazyConfigContext getInstance(){
        if(instance == null){
            synchronized (LazyConfigContext.class){
                if(instance == null){
                    instance = new LazyConfigContext();
                }
            }
        }

        return instance;
    }

}


//饿汉式
class ConfigContext{

    //在类创建的时候就实例化好
    private static final ConfigContext instance = new ConfigContext();

    //将构造方法私有话
    private ConfigContext(){}

    public static ConfigContext getInstance(){
        return instance;
    }
}
