package designPatterns.structure;

import java.util.HashMap;

/**
 * @Author yongzhi
 * @Date 2021/3/19 17:10
 **/
public class SharedConcreteTest {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight f01 = factory.getFlyweight("a");
        Flyweight f02 = factory.getFlyweight("a");
        Flyweight f03 = factory.getFlyweight("a");
        Flyweight f11 = factory.getFlyweight("b");
        Flyweight f12 = factory.getFlyweight("b");
        f01.operation(new UnsharedConcreteFlyweight("red","第1次调用a。"));
        f02.operation(new UnsharedConcreteFlyweight("black","第2次调用a。"));
        f03.operation(new UnsharedConcreteFlyweight("blue","第3次调用a。"));
        f11.operation(new UnsharedConcreteFlyweight("red","第1次调用b。"));
        f12.operation(new UnsharedConcreteFlyweight("red","第2次调用b。"));
    }
}

//非享元对象
class UnsharedConcreteFlyweight{
    private String color;
    private String info;

    public UnsharedConcreteFlyweight(String color,String info){
        this.info = info;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getInfo() {
        return info;
    }
}

//抽象享元角色
interface Flyweight{
    public void operation(UnsharedConcreteFlyweight state);
}

//具体的享元角色
class ConcreteFlyweight implements Flyweight{
    private String key;

    ConcreteFlyweight(String key){
        this.key = key;
    }

    @Override
    public void operation(UnsharedConcreteFlyweight state) {
        System.out.println("具体享元key被调用：" + this.key  );
        System.out.println("非享元的信息：" + state.getColor()  + ":" + state.getInfo());
    }
}

//享元工厂角色
class FlyweightFactory{
    private HashMap<String,Flyweight> map = new HashMap<String, Flyweight>();

    public Flyweight getFlyweight(String key){
        Flyweight flyweight = map.get(key);
        if(flyweight == null){
            //说明具体的享元对象没有存在 需要创建并加入到map里面
            flyweight = new ConcreteFlyweight(key);
            map.put(key,flyweight);
        }

        return flyweight;
    }
}