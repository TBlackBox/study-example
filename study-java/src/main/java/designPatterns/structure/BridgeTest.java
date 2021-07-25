package designPatterns.structure;

/**
 * 桥接模式
 *
 * 女生衣服的选择
 * 衣服有很多种，按照颜色分  红色 和黑色
 * 按照 厚薄分  厚 和 薄的去 还可按照其他形式分 这里只是举例
 * 按照衣服类型  分为 风衣 和雨衣
 * @Author yongzhi
 * @Date 2021/3/19 14:10
 **/
public class BridgeTest {
    public static void main(String[] args) {

        //客服端就可以进行搭配
        Color color = new Red();
        Dress dress = new WindDress();
        //这里设置颜色
        dress.setColor(color);

        String name = dress.getName();
        System.out.println(name);
    }
}

//实现话角色
interface Color{
    String getColor();
}

//具体的颜色
class Red implements Color{

    @Override
    public String getColor() {
        return "红色";
    }
}
//具体的颜色
class Black implements Color{
    @Override
    public String getColor() {
        return null;
    }
}

//抽象化角色 衣服
abstract class Dress{

    protected Color color;

    public void setColor(Color color){
        this.color = color;
    }

    public abstract String getName();
}

//扩展抽象化角色  风衣
class WindDress extends Dress{

    @Override
    public String getName() {
        return color.getColor() + "风衣";
    }
}
//扩展抽象化角色  雨衣
class RainDress extends Dress{
    @Override
    public String getName() {
        return color.getColor() + "雨衣";
    }
}