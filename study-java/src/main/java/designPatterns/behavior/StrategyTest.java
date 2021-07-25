package designPatterns.behavior;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * 策略模式
 * @Author yongzhi
 * @Date 2021/3/22 13:50
 **/
public class StrategyTest {
    public static void main(String[] args) {

        Context c = new Context();
        Strategy s = new ConcreteStrategyB();
        c.setStrategy(s);

        //这里进行逻辑调用
        c.strategyMethod();
        //也可以通过c.getContext  获取的策略实现类进行方法的调用
    }
}

//抽象策略类
interface Strategy{
    public void strategyMethod();
}

//具体策略类A
class ConcreteStrategyA implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("具体的实现方法B");
    }
}

//具体策略类B
class ConcreteStrategyB implements  Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("具体实现方法B");
    }
}

//环境类  这个也也可管理里 从中获取具体的算法
class Context{
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        strategy.strategyMethod();
    }
}
