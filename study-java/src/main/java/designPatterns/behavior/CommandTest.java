package designPatterns.behavior;

/**
 * 命令模式
 * @Author yongzhi
 * @Date 2021/3/22 14:38
 **/
public class CommandTest {
    public static void main(String[] args) {

        Command c = new ConcreteCommand();

        Invoker i = new Invoker(c);
        //调用方法
        i.call();
    }
}
//调用者
class Invoker{

    private Command command;

    public Invoker(Command command){
        this.command = command;
    }

    public void setCommand(Command command){
        this.command = command;
    }

    public void call(){
        System.out.println("调用者执行命令。。。");
        command.execute();
    }
}

class Receiver{
    public void action(){
        System.out.println("接收者active()方法被调用");
    }
}

interface Command{
    public abstract void execute();
}

class ConcreteCommand implements Command{

    private Receiver receiver;

    ConcreteCommand(){
        receiver = new Receiver();
    }

    @Override
    public void execute() {
        receiver.action();
    }
}