package designPatterns.structure;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 组合模式
 * @Author yongzhi
 * @Date 2021/3/19 17:51
 **/
public class CompositePatternTest {
    public static void main(String[] args) {

        //树枝节点
        Component c = new Composite();

        //在添加一个数枝节点
        Component c1 = new Composite();

        c.add(c1);

        //树叶节点
        Component l = new Leaf("1.text");

        Component l1 = new Leaf("2.text");

        //将l l1 添加到c1
        c.add(l);
        c1.add(l1);

        c.operation();

        /**
         * 安全组合模式
         */
//        //将树枝构件类型更改为 Composite 类型，以便获取管理子类操作的方法。
//        //树枝节点
//        Composite cc = new Composite();
//
//        //在添加一个数枝节点
//        Composite cc1 = new Composite();
//
//        c.add(c1);
//
//        //树叶节点
//        Component1 ll = new Leaf("1.text");
//
//        Component ll1 = new Leaf("2.text");
//        cc1.add(ll);
//        cc1.add(ll1);
//
//        cc.operation();
    }
}

//抽象构构件
interface Component{
    public void add(Component c);

    public void remove(Component c);

    public Component getChild(int i);

    public void operation();
}

//树叶构建
class Leaf implements Component{
    private String name;

    public Leaf(String name){
        this.name = name;
    }

    @Override
    public void add(Component c) {

    }

    @Override
    public void remove(Component c) {

    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println("树叶:" + name + "  被访问！");
    }
}

//树枝构建
class Composite implements Component{
    private ArrayList<Component> children = new ArrayList<>();

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public Component getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        System.out.println("这个是树枝节点，这里可以选择可以进行一些操作");
        for (Component c : children){
            c.operation();
        }
    }
}
//安全组合模式
//安全式的组合模式与透明式组合模式的实现代码类似，只要对其做简单修改就可以了，代码如下。
interface Component1 {
    public void operation();
}