package designPatterns.create;

/**
 * @Author yongzhi
 * @Date 2021/3/18 14:00
 **/
public class methonFactory {
    public static void main(String[] args) {

        Animal1 animal;
        AbstractFatory af;
        //这里可以通过读取配置文件或者其他地方获取动物工厂 获取动物对象
        //也可以通过配置文件获取具体动物的工厂名字  通过反射实列话对象
        //这里模拟使用狗工厂产生狗
        af = (AbstractFatory)new DogFatory();
        animal = af.newAnimal();
        animal.call();
    }
}

//动物接口
interface Animal1{
    void call();
}

//具体的动物  毛
class Cat1 implements  Animal1{
    @Override
    public void call() {
        System.out.println("猫叫");    }
}

//具体的动物 狗
class Dog1 implements  Animal1{
    @Override
    public void call() {
        System.out.println("狗叫");
    }
}

//抽象工厂 狗
interface AbstractFatory{
    public Animal1 newAnimal();
}

//具体的猫工厂
class CatFatory implements  AbstractFatory{
    @Override
    public Animal1 newAnimal() {
        return new Cat1();
    }
}

//具体的狗工厂
class DogFatory implements AbstractFatory{
    @Override
    public Animal1 newAnimal() {
        return new Dog1();
    }
}