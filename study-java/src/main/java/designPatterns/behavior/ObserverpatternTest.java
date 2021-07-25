package designPatterns.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * 观察者模式
 * @Author yongzhi
 * @Date 2021/3/22 17:14
 **/
public class ObserverpatternTest {
    public static void main(String[] args) {
        Subject s = new ConcreteSubject();

        Observer1 s1 = new ConcreteObserver1();
        Observer1 s2 = new ConcreteObserver2();

        s.add(s1);
        s.add(s2);

        //通知
        s.notifyobServer();

    }
}

//抽象目标
abstract class Subject{
    protected List<Observer1> observers = new ArrayList<>();

    public void add(Observer1 observer){
        observers.add(observer);
    }

    public void remove(Observer1 observer){
        observers.remove(observer);
    }

    //通知观察者方法
    public abstract void notifyobServer();
}

//具体的目标
class ConcreteSubject extends Subject{

    @Override
    public void notifyobServer() {
        System.out.println("目标行为发生改变，");

        //让观察都先赢
        for(Observer1 o  : observers){
            o.response();
        }
    }
}

//抽象观察者
interface Observer1 {
    void response(); //反应
}

//具体观察者1
class ConcreteObserver1 implements Observer1 {
    public void response() {
        System.out.println("具体观察者1作出反应！");
    }
}
//具体观察者1
class ConcreteObserver2 implements Observer1 {
    public void response() {
        System.out.println("具体观察者2作出反应！");
    }
}