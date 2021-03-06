package cn.javafroum.mybatis.springboot2.okhttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.javafroum.mybatis.springboot2.SpringbootApplication;
import cn.javafroum.mybatis.springboot2.util.OkHttpUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootTestOkHttp {

	@Test
	public void testGet() throws IOException {
		String url = "http://www.baidu.com";
		Map<String, String> queries = new HashMap<String, String>();
		
		System.out.println(OkHttpUtil.get(url));
	}
}
