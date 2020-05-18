package test;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class UtilTest {
    @BeforeClass
    public static void initClass(){
        System.out.println("调用一次，在第一个测试方法调用前");
    }
    
    @AfterClass
    public static void afterClass(){
        System.out.println("调用一次，最后一个测试方法调用后");
    }
    
    @Before
    public void initMethod(){
        System.out.println("在每个测试方法开始前调用");
    }
    
    @After
    public void afterMethod(){
        System.out.println("在每个测试方法完成后调用");
    }

    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals(2, add(1,1));
    }
    
    //超时将会报异常
    @Test(timeout=500)
    public void testTimeout(){
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
        
        }
    }
    
    //期待抛出异常的测试用例，测试方法中如果不抛出异常则失败
    @Test(expected=IndexOutOfBoundsException.class)
    public void testException(){
        new LinkedList<String>().get(0);
    }
    
    @Ignore("ignore the test")
    @Test
    public void ignoreTest() throws Exception {
    	System.out.println("Ignore");
        Assert.assertEquals(2, add(1,1));
    }
        
    public int add(int n1, int n2){
        return n1 + n2;
    }
}
