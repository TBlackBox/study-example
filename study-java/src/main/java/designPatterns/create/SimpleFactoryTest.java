package designPatterns.create;

/**
 * 简单工厂设计模式
 * @Author yongzhi
 * @Date 2021/3/17 20:03
 **/
public class SimpleFactoryTest {

    public static void main(String[] args) {

        //生成狗 这样生产都非常容易了
        Dog dog =(Dog) SimpleFactory.createAnimal(AnimalType.DOG);
        dog.call();
    }
}

//抽象产品 这里以动物为列
interface Animal{
    //叫
    void call();
}

//具体产品1
class Cat implements Animal{

    @Override
    public void call() {
        System.out.println("猫叫");
    }
}

//具体产品2
class  Dog implements Animal{

    @Override
    public void call() {
        System.out.println("狗叫");
    }
}

//动物类型  定义一个枚举  方管理
enum AnimalType{
    CAT,
    DOG
}

//工厂类
class SimpleFactory{

    public static Animal createAnimal(AnimalType type){
        switch (type){
            case CAT:
                return new Cat();
            case DOG:
                return new Dog();

        }
        return null;
    }
}