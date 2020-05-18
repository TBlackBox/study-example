package hello;

public class Hello {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Hello(){
        System.out.println("执行了无参构造方法");
    }

    public Hello(String name) {
        System.out.println("执行了有参构造方法");
        this.name = name;
    }

    public void init1(){
        System.out.println("生命周期，初始化调用");
    }

    public void destory1(){
        System.out.println("生命周期，结束bean调用");
    }


}
