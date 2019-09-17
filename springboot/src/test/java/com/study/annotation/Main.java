package com.study.annotation;

import java.lang.reflect.Field;

/**
 * 注解测试
 * @author Administrator
 *
 */
public class Main {
    public static void main(String[] args) {
        Field[] fields = Apple.class.getDeclaredFields();
        for (Field field : fields) {
            //System.out.println(field.getName().toString());
            if (field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = field.getAnnotation(FruitName.class);
                System.out.println("水果的名称：" + fruitName.value());
            }else if (field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                System.out.println("水果的颜色："+fruitColor.fruitColor());
            }else if (field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                System.out.println("水果供应商编号:" + fruitProvider.id() + " 名称:" + fruitProvider.name() + " 地址:" + fruitProvider.address());
            }
        }
        
        System.out.println("================================================");
        MyAnnotation myAnnotation = TestAnnotation.class.getAnnotation(MyAnnotation.class);
        System.out.println("neme:"+myAnnotation.name());
        System.out.println("年龄："+myAnnotation.age());
        System.out.println("value:"+myAnnotation.value());
        System.out.println("是否是学生:" + myAnnotation.isStudent());
        System.out.println("别名:"+ myAnnotation.alias()[0] + "和" + myAnnotation.alias()[1] );
        System.out.println("颜色：" + myAnnotation.favoriteColor());
    }

}
