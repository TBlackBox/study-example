package com.study.example.springboot;

import com.study.example.springboot.cache.JsonRedisTemplate;
import com.study.example.springboot.entity.User;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.LinkedHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Autowired
	RedisTemplate redisTemplate;

    @Autowired
	StringRedisTemplate stringRedisTemplate;

    @Autowired
	JsonRedisTemplate jsonRedisTemplate;

//    @Autowired
//	RabbitTemplate rabbitTemplate;

	@Autowired
	JestClient jestClient;

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void testSpringDateEs(){
		elasticsearchTemplate.createIndex("test");
	}


	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	public void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();
		//邮件设置
		message.setSubject("通知-今晚开会");
		message.setText("今晚7:30开会");

		message.setTo("17512080612@163.com");
		message.setFrom("534096094@qq.com");

		mailSender.send(message);
	}

	@Test
	public void test02() throws  Exception{
		//1、创建一个复杂的消息邮件
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		//邮件设置
		helper.setSubject("通知-今晚开会");
		helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);

		helper.setTo("17512080612@163.com");
		helper.setFrom("534096094@qq.com");

		//上传文件
		helper.addAttachment("1.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\1.jpg"));
		helper.addAttachment("2.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\2.jpg"));

		mailSender.send(mimeMessage);

	}



	@Test
	public void testJestClient() throws IOException {

		//1. 构建一个索引功能
		// user是要存的参数  test 是索引  tag1 是类型
		Index index = new Index.Builder("user").index("test").type("tag1").build();
		//2. 执行
		jestClient.execute(index);

		//搜索
		//查询表达式
		String json = "{\n" +
				"    \"query\" : {\n" +
				"        \"match_all\" : {}\n" +
				"    }\n" +
				"}'";
		//构建搜索功能
		Search search = new Search.Builder(json).addIndex("test").addType("type").build();
		SearchResult  searchResult = jestClient.execute(search);
		//通过searchResult获取信息
		System.out.println(searchResult.getJsonObject());
	}


//
//    @Test
//	public void testRabbitRecMsg(){
//    	//通过receive 接受消息  message 是个消息体  需要解析
//		Message message = rabbitTemplate.receive(queueName);
//    	//message 队列名字  接到的消息是object  消息对象
//		//Object o = rabbitTemplate.convertSendAndReceive(exchange, routingKey, message);
//	}


    @Test
	public void testRabbitMqSendMsg(){
    	//发送消息  message 需要封装，定义消息体体和消息头
		//exchange 交换器名字
		//routingKey  路由可
		//message 消息
		//rabbitTemplate.send(exchange, routingKey, message);

		//发送红简易消息，不需要封装消息，object 默认当成消息体，发送给RabbitMq
		//发送的消息默认用Java序列化的方式存储到RabbitMq队列中（当然可以改序列化的方式）
		//rabbitTemplate.convertAndSend(exchange, routingKey, object);
	}


	@Test
	public void contextLoads() {
		redisTemplate.opsForSet().add("aa","aaa");
		User user = new User();
		user.setId(1);
		user.setName("哈哈");
		redisTemplate.opsForValue().set("user",user);
		User user1 = (User) redisTemplate.opsForValue().get("user");
		System.out.println(user1.getName());
	}

	@Test
	public void testRedis() throws JSONException {
		User user = new User();user.setId(1);
		user.setName("哈哈");
		jsonRedisTemplate.opsForValue().set("user",user);
		Object obj = jsonRedisTemplate.opsForValue().get("user");
		LinkedHashMap map = (LinkedHashMap) obj;
		JSONObject json = new JSONObject(map);
		System.out.println(json.getString("name"));
	}


}
