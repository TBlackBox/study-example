import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * @Author yongzhi
 * @Date 2021/3/15 11:34
 **/

public class ReferenceTest {

    @Test
    public void testWeakReference() throws InterruptedException {

        //强引用
        String str = new String("test");

        WeakReference<String> weakReference = new WeakReference<>(str);

        str = null;

//        System.gc();

//        Thread.sleep(10000);

        //弱引用转强引用
        String test1 =  weakReference.get();
        System.out.println(test1);

        Object obj1 = new Object();

        Object obj2 = new Object();

        Integer a = 124512;
        Integer b = 124512;

        System.out.println(a == b);
        System.out.println(a.equals(b));

        Class<? extends  Number> intClass = int.class;
        intClass = double.class;

        intClass = Float.TYPE;

    }
}
