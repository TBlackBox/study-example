package designPatterns.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式  实现记事本的回退功能
 * @Author yongzhi
 * @Date 2021/3/23 14:29
 **/
public class MementoPatternTest {
    public static void main(String[] args) {

        Originator originator = new Originator();

        Caretaker caretaker = new Caretaker();

        originator.setVersion(1);
        originator.setContent("初始化内容");

        //添加到备忘录
        caretaker.setMemento(originator.createMenento());

        originator.setVersion(2);
        originator.setContent("第二版本");
        //将第二版本添加到备忘录
        caretaker.setMemento(originator.createMenento());

        originator.setVersion(3);
        originator.setContent("第三版本");
        //将第三版本添加到备忘录
//        caretaker.setMemento(originator.createMenento());

        //获取当前版本
        System.out.println("当前版本号："+originator.getVersion() + "  内容：" + originator.getContent());

        //回退一个版本
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("第一次回退后版本号："+originator.getVersion() + "  内容：" + originator.getContent());

        //回退两个版本
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("第二次回退后版本号："+originator.getVersion() + "  内容：" + originator.getContent());

    }
}
//备忘录 用于记录历史版本的内容
class Memento{
    private int version;
    private String context;

    public Memento(int version,String context){
        this.version = version;
        this.context = context;
    }
    public String getContext(){
        return this.context;
    }
    public int getVersion(){
        return this.version;
    }

    public void setContext(String content){
        this.context = content;
    }
    public void setVersion(int version){
        this.version = version;
    }
}

//发起人  相当于记事本
class Originator{
    private int version;
    private String content;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //创建备忘录
    public Memento createMenento(){
        return new Memento(version,content);
    }

    //恢复功能
    public void restoreMemento(Memento m){
        this.setVersion(m.getVersion());
        this.setContent(m.getContext());
    }
}

//管理者  用于管理备忘录的
class Caretaker{
    private int index = -1;
    private List<Memento> list = new ArrayList<>();

    public Memento getMemento() {
        if(index == -1){
            return null;
        }
        Memento m = list.get(index);
        index--;
        return m;
    }

    public void setMemento(Memento memento) {
        list.add(++index,memento);
    }
}

