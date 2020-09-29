package string;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
public class TestString {
   public static void main(String[] args) {
	   
	   int length = 100000;
	   
       //plus拼接字符串方式
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            s = s + String.valueOf(i);
        }
        long te = System.currentTimeMillis();
        System.out.println("Plus cost {"+( te - ts) +"} ms");
        //concat拼接字符串方式
        String s2 = "";
        long ts2 = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            s2 = s2.concat(String.valueOf(i));
        }
        long te2 = System.currentTimeMillis();
        System.out.println("concat cost {"+(te2 - ts2)+"} ms");
        //StringUtils.join拼接字符串方式
        List<String> list = new ArrayList<String>();
        long ts3 = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            list.add(String.valueOf(i));
        }
        StringUtils.join(list, "");
        long te3 = System.currentTimeMillis();
        System.out.println("StringUtils.join cost {"+(te3 - ts3)+"} ms");
        //StringBuffer拼接字符串方式
        StringBuffer sb = new StringBuffer();
        long ts4 = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te4 = System.currentTimeMillis();
        System.out.println("StringBuffer cost {"+(te4 - ts4)+"} ms");
        //StringBuilder拼接字符串方式
        StringBuilder sb5 = new StringBuilder();
        long ts5 = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            sb5.append(String.valueOf(i));
        }
        sb5.toString();
        long te5 = System.currentTimeMillis();
        System.out.println("StringBuilder cost {"+(te5 - ts5)+"} ms");
   }
}