package designPatterns.behavior;

/**
 * 状态模式
 * @Author yongzhi
 * @Date 2021/3/22 16:39
 **/
public class StateTest {
    public static void main(String[] args) {
        Context1 context = new Context1();    //创建环境
        context.Handle();    //处理请求
        context.Handle();
        context.Handle();
        context.Handle();
    }
}

//环境类
class Context1 {
    private State state;
    //定义环境类的初始状态
    public Context1() {
        //默认状态
        this.state = new ConcreteStateA();
    }
    //设置新状态
    public void setState(State state) {
        this.state = state;
    }
    //读取状态
    public State getState() {
        return (state);
    }
    //对请求做处理
    public void Handle() {
        state.handle(this);
    }
}


//抽象状态
abstract class State{
    public abstract void handle(Context1 context);
}

//具体状态A类
class ConcreteStateA extends State{

    @Override
    public void handle(Context1 context) {
        System.out.println("当前状态是A");
        context.setState(new ConcreteStateB());
    }
}
//具体状态B类
class ConcreteStateB extends State{

    @Override
    public void handle(Context1 context) {
        System.out.println("当前状态是A");
        context.setState(new ConcreteStateA());
    }
}
