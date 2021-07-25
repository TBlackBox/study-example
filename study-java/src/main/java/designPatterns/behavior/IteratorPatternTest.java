package designPatterns.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * @Author yongzhi
 * @Date 2021/3/23 10:32
 **/
public class IteratorPatternTest {
    public static void main(String[] args) {

        Aggregate<Integer> a = new ConcreteAggregate<>();
        a.add(1);
        a.add(10);
        a.add(20);

        Iterator<Integer> iterator = a.getIterator();

        //进行遍布
        while (iterator.hasNext()){
            Integer i = iterator.next();
            System.out.println(i  + "\t");

        }
    }
}

//抽象聚合
interface Aggregate<T>{
    public void add(T t);

    public void remove(T t);

    public Iterator getIterator();
}

//具体聚合
class ConcreteAggregate<T> implements Aggregate<T>{

    private List<T> list = new ArrayList<>();

    @Override
    public void add(T t) {
        list.add(t);
    }

    @Override
    public void remove(T t) {
        list.remove(t);
    }

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator<T>(list);
    }
}

//抽象迭代器
interface Iterator<T>{

    T fist();

    T next();

    boolean hasNext();
}

//具体的迭代器
class ConcreteIterator<T> implements Iterator<T>{

    List<T> list = null;
    Integer index = -1;

    public ConcreteIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public T fist() {
        index = 0;
        return list.get(index);
    }

    @Override
    public T next() {
        if(this.hasNext()){
            return this.list.get(++index);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if(index < list.size() -1){
            return true;
        }
        return false;
    }
}
