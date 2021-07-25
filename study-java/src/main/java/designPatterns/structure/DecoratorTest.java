package designPatterns.structure;

/**
 * 装饰器模式
 *  泰克 可以转换角色 可以是老师  回家可以是父母，但上班穿的是工作服，下班穿的这是休闲服  来举例
 * @Author yongzhi
 * @Date 2021/3/19 15:41
 **/
public class DecoratorTest {
    public static void main(String[] args) {

        //构建 泰克
        Taike taike = new TaikeOriginal();
        System.out.println(taike.display());

        //老师
        Taike teacher = new TeacherTaike(taike);
        System.out.println(teacher.display());

        //父母
        Taike parent = new ParentTaike(taike);
        System.out.println(parent.display());
    }
}

//抽象构建角色 泰克
interface  Taike{
    public String display();
}
//具体的构建角色： 原身
class  TaikeOriginal implements Taike{

    private String head = "头";

    private String body = "裸体";

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String display() {

        return head + "-" +body;
    }
}

//抽象装饰角色： 变形
class Changer implements Taike{
    Taike taike;

    public Changer(Taike taike){
        this.taike = taike;
    }

    @Override
    public String display() {
        return taike.display();
    }
}

//具体的装饰角色： 老师
class TeacherTaike extends Changer{

    public TeacherTaike(Taike taike) {
        super(taike);
    }

    public String display(){
        //进行改变和装饰
        setChanger();
        return super.display();
    }

    public void setChanger(){
        ((TaikeOriginal)super.taike).setHead("带老师的帽子");
        ((TaikeOriginal)super.taike).setBody("穿老师的工作服");
    }
}

//具体的装饰角色; 父母
class ParentTaike extends Changer{

    public ParentTaike(Taike taike) {
        super(taike);
    }
    public String display(){
        //进行改变和装饰
        setChanger();
        return super.display();
    }

    public void setChanger(){
        ((TaikeOriginal)super.taike).setHead("没有带帽子，中分");
        ((TaikeOriginal)super.taike).setBody("穿休闲服");
    }
}
