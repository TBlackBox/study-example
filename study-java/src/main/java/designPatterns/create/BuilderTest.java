package designPatterns.create;

/**
 * 建造者模式
 * @Author yongzhi
 * @Date 2021/3/18 14:41
 **/
public class BuilderTest {
    public static void main(String[] args) {

        //调用
        Builder builder = new createBuilder();
        Director director = new Director(builder);
        Car car = director.construct();

        //车的展示
        String showCar = car.show(); //重庆的车轮-福建的玻璃-成都的发动机-北京的框架
        System.out.println(showCar);

        //第二种构建方式
        Student student = new Student.Builder()
                .setName("老王")
                .setAddress("重庆")
                .setAge(12)
                .build();

        System.out.println(student.toString());
    }
}

// 产品角色：包含多个组成部件的复杂对象。
class Car{
    //轮胎
    private String tires;
    //玻璃
    private String glass;
    //发动机
    private String engine;
    //车架
    private String frame;

    public void setTires(String tires) {
        this.tires = tires;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String show(){
        //把车展示出来
        return tires + "-" + glass + "-" + engine + "-" + frame;
    }
}

//抽象建造者：包含创建产品各个子部件的抽象方法。
abstract class Builder{
    //创建产品对象
    protected Car car = new Car();

    public abstract String buildTires();

    public abstract String buildGlass();

    public abstract String buildEngine();

    public abstract String buildFrame();

    public Car getResult(){
        return car;
    }
}

//具体建造者：实现了抽象建造者接口。
class createBuilder extends Builder{

    @Override
    public String buildTires() {
        car.setTires("重庆的车轮");
        return "重庆的车轮";
    }

    @Override
    public String buildGlass() {
        car.setGlass("福建的玻璃");
        return "福建的玻璃";
    }

    @Override
    public String buildEngine() {
        car.setEngine("成都的发动机");
        return "成都的发动机";
    }

    @Override
    public String buildFrame() {
        car.setFrame("北京的框架");
        return "北京的框架";
    }
}

//指挥者：调用建造者中的方法完成复杂对象的创建。
class Director{
    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public Car construct(){
        builder.buildEngine();
        builder.buildTires();
        builder.buildFrame();
        builder.buildGlass();
        return builder.getResult();
    }
}

//第二种建造者模式
class Student{
    private String name;
    private int age;
    private String address;

    private Student(Builder builder){

        this.name = builder.name;
        this.address = builder.address;
        this.age = builder.age;
    }

    public static class Builder{
        private String name ;
        private int age = 0;
        private String address;

        public Builder(){}

        public Builder(String name){
            this.name = name;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Builder setAddress(String address){
            this.address = address;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}


