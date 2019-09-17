package com.study.annotation;
@MyAnnotation(
        value = "王麻子",
        name = "小明",
        age = 32,
        isStudent = false,
        alias = {"二狗", "狗蛋"},
        favoriteColor = MyAnnotation.Color.RED
)
public class TestAnnotation {
    //使用MyAnnotation注解，该类生成的javadoc文档包含注解信息如下：
    /*
    @MyAnnotation(value = "info", name = "myname", age = 99, isStudent = false, alias = {"name1","name2"}, favoriteColor = Color.RED)
    public class MyClass
    extends Object
     */
}