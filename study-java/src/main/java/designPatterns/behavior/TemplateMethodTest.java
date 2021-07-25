package designPatterns.behavior;

/**
 * 模板方法模式
 * @Author yongzhi
 * @Date 2021/3/22 12:06
 **/
public class TemplateMethodTest {
    public static void main(String[] args) {

        AbstractClass a = new ConcreteClass();
        a.TemplateMethod();
    }
}

//抽象类
abstract class AbstractClass{

    //模板方法
    public void TemplateMethod(){
        //具体方法
        specificMethod();

        //抽象方法
        abstractMethod();

        abstractMethod1();
    }

    public void specificMethod(){
        System.out.println("具体方法，也可以是公共方法");
        //在具体方法中也能调用抽象方法
//        abstractMethod();
    }

    abstract void abstractMethod();

    abstract void abstractMethod1();
}

//具体子类
class ConcreteClass extends AbstractClass{

    @Override
    void abstractMethod() {
        System.out.println("抽象方法被调用");
    }

    @Override
    void abstractMethod1() {
        System.out.println("抽象方法1被调用");
    }
}
