package designPatterns.create;

/**
 * 原型模式
 * @Author yongzhi
 * @Date 2021/3/17 19:31
 **/
public class RealizetypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType realizeType = new RealizeType();

        RealizeType realizeTypeClone = (RealizeType)realizeType.clone();

        System.out.println(realizeType == realizeTypeClone); //false
    }

}

/**
 * 浅克隆  创建一个新对象，新对象的属性和原来对象完全相同，对于非基本类型属性，仍指向原有属性所指向的对象的内存地址。
 *深克隆更复杂  需要把非基本属性也克隆
 */
//克隆原型  Cloneable原型接口
class RealizeType implements Cloneable{

    RealizeType(){
        System.out.println("创建对象成功");
    }

    public Object clone() throws CloneNotSupportedException {
        System.out.println("复制具体的原型");

        return (RealizeType)super.clone();
    }
}