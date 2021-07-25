package designPatterns.structure;

import java.lang.annotation.Target;

/**
 * 适配者模式
 * @Author yongzhi
 * @Date 2021/3/19 11:43
 **/
public class AdapteeTest {
    public static void main(String[] args) {

        //类适配者模式测试
        Target1 target = new ClassAdapter();
        target.request();

        //对象是配置模式测试
        Adaptee adaptee = new Adaptee();
        Target1 target1 = new ObjectAdaptee(adaptee);
        target1.request();
    }
}

//目标接口
interface Target1{
    public void request();
}

//适配者接口或类
class Adaptee{
    public void realRequest(){
        System.out.println("真实的请求");
    }
}

//类适配器类(第一种适配器方法)
class ClassAdapter extends Adaptee implements Target1{

    @Override
    public void request() {
        //这里进行适配逻辑
        super.realRequest();
    }

}

//对象适配器类(第二种适配形式)
class ObjectAdaptee implements Target1{

    private Adaptee adaptee;

    public ObjectAdaptee(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        this.adaptee.realRequest();
    }
}