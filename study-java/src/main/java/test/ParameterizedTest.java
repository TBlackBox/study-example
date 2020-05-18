package test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *  参数化测试
 * @author Administrator
 *
 */

@RunWith(Parameterized.class)
public class ParameterizedTest {
    private int param;
    private boolean result;
    private String name;

    //为每组数据构建测试用例
    public ParameterizedTest(int param, boolean result,String name) {
        this.param = param;
        this.result = result;
        this.name = name;
    }

    // 生成测试数据
    @Parameterized.Parameters
    public static Collection<Object[]> genParams() {
        return Arrays.asList(new Object[][]{{1, true,"BlackBox"}, {2, false,"歪歪"}});
    }

    //测试代码
    @Test
    public void test() {
    	System.out.println("this.param:" + this.param);
    	System.out.println("this.result:" + this.result);
    	System.out.println("this.name:" + this.name);
        Assert.assertEquals(this.param % 2 == 1, this.result);
    }
}