package designPatterns.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 * @Author yongzhi
 * @Date 2021/3/23 11:58
 **/
public class VisitorPatternTest {
    public static void main(String[] args) {

        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());

        Visitor visitor = new ConcreteVisitorA();
        os.accept(visitor);
    }
}

//抽象访问者
interface Visitor{
    void visit(ConcreteElementA element);

    void visit(ConcreteElementB element);
}

//具体访问者A类
class ConcreteVisitorA implements Visitor {
    public void visit(ConcreteElementA element) {
        System.out.println("具体访问者A访问-->" + element.operationA());
    }
    public void visit(ConcreteElementB element) {
        System.out.println("具体访问者A访问-->" + element.operationB());
    }
}
//具体访问者B类
class ConcreteVisitorB implements Visitor {
    public void visit(ConcreteElementA element) {
        System.out.println("具体访问者B访问-->" + element.operationA());
    }
    public void visit(ConcreteElementB element) {
        System.out.println("具体访问者B访问-->" + element.operationB());
    }
}

//抽象元素类
interface Element{
    void accept(Visitor visitor);
}

//具体元素A类
class ConcreteElementA implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA(){
        return "具体元素A的操作";
    }
}

//具体元素B类
class ConcreteElementB implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB(){
        return "具体元素B的操作";
    }
}

//对象结构角色
class ObjectStructure {
    private List<Element> list = new ArrayList<Element>();
    public void accept(Visitor visitor) {
        java.util.Iterator<Element> i = list.iterator();
        while (i.hasNext()) {
            ((Element) i.next()).accept(visitor);
        }
    }
    public void add(Element element) {
        list.add(element);
    }
    public void remove(Element element) {
        list.remove(element);
    }
}