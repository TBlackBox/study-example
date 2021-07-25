package designPatterns.behavior;

/**
 * 责任链模式
 * @Author yongzhi
 * @Date 2021/3/22 15:49
 **/
public class ChainOfResponsibilityTest {
    public static void main(String[] args) {

        Handler h1 = new ConcreteHandlerA();
        Handler h2 = new ConcreteHandlerB();

        h1.setNext(h2);

        h1.handleRequest("B");
    }
}

//抽象处理角色
abstract class Handler{
    private Handler next;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    abstract  void handleRequest(String request);
}

//具体处理中A
class ConcreteHandlerA extends Handler{

    @Override
    void handleRequest(String request) {
        if (request.equals("A")){
            System.out.println("具体A的处理逻辑");
        }else{
            if(getNext() != null){
                //交给下一个处理器处理啊
                getNext().handleRequest(request);
            }else{
                System.out.println("没有处理器处理该请求");
            }
        }
    }
}

//具体处理中A
class ConcreteHandlerB extends Handler{

    @Override
    void handleRequest(String request) {
        if (request.equals("B")){
            System.out.println("具体B的处理逻辑");
        }else{
            if(getNext() != null){
                //交给下一个处理器处理啊
                getNext().handleRequest(request);
            }else{
                System.out.println("没有处理器处理该请求");
            }
        }
    }
}