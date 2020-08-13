package cn.javafroum.mybatis.springboot2.okhttp;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.lang.Nullable;

import com.alibaba.fastjson.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class TestOkHttp {

	/*
	 * 基本的get请求
	 */
	@Test
	public void testOkHttpGet() throws IOException {
		
		String url = "http://127.0.0.1/okhttp/get?username=王麻子";
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
			      .url(url)
			      .build();

		  try (Response response = client.newCall(request).execute()) {
		    String retValue = response.body().string();
		    //返回值:测试GET成功,用户名：王麻子
		    System.out.println("返回值:" + retValue);
		  }
	}
	
	/**
	 * 异步get请求
	 */
	@Test
	public void testOkHttpGetAsyn() {
		String url = "http://127.0.0.1/okhttp/get?username=王麻子";
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
		        .url(url)
		        .get()//默认就是GET请求，可以不写
		        .build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
		    @Override
		    public void onFailure(Call call, IOException e) {
		       System.out.println("请求失败");
		    }

		    @Override
		    public void onResponse(Call call, Response response) throws IOException {
		        System.out.println("相应数据: " + response.body().string());
		    }
		});
		
		System.out.println("请求结束");
	}
	
	
	
	/**
	 *  POST方式提交String
	 *  这种方式与前面的区别就是在构造Request对象时，需要多构造一个RequestBody对象，用它来携带我们要提交的数据。
	 *  在构造 RequestBody 需要指定MediaType，用于描述请求/响应 body 的内容类型
	 */
//	public static RequestBody create(final @Nullable MediaType contentType, final byte[] content) {}
//	 public static RequestBody create(final @Nullable MediaType contentType, final ByteString content) {}
	
	public static final MediaType mediaType
    = MediaType.get("application/json; charset=utf-8");  
	@Test
	public void testOkHttpPost() throws IOException {
			
		String url = "http://127.0.0.1/okhttp/post";
		
		OkHttpClient client = new OkHttpClient();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "老王");
		String parame = jsonObject.toString();
		
		RequestBody body = RequestBody.create(mediaType,parame);
		
		Request request = new Request.Builder()
		      .url(url)
		      .post(body)
		      .build();
		try (Response response = client.newCall(request).execute()) {
			  String retValue = response.body().string();
			  System.out.println("返回值:" + retValue);
		}
	}
	
	/**
	 * POST方式提交表单
	 * 提交表单时，使用 RequestBody 的实现类FormBody来描述请求体，它可以携带一些经过编码的 key-value 请求体，
	 * 键值对存储在下面两个集合中：
	 * private final List<String> encodedNames;
	 *private final List<String> encodedValues;
	 */
	@Test
	public void testOkHttpPostForm() throws IOException {
			
		String url = "http://127.0.0.1/okhttp/post";
		
		OkHttpClient client = new OkHttpClient();
		
		FormBody.Builder builder = new FormBody.Builder();
		
		builder.add("username", "王麻子");
		
		Request request = new Request.Builder()
		      .url(url)
		      .post(builder.build())
		      .build();
		try (Response response = client.newCall(request).execute()) {
			  String retValue = response.body().string();
			  //返回值:测试POST成功,用户名：勇智
			  System.out.println("返回值:" + retValue);
		}
	}
	
	/**
	 * POST 提交文件
	 */
	@Test
	public void testOkHttpPostFile() {
		String url = "http://127.0.0.1/okhttp/file";
		MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
		OkHttpClient okHttpClient = new OkHttpClient();
		File file = new File("D:\\test.md");
		
		Request request = new Request.Builder()
		        .url(url)
		        .post(RequestBody.create(mediaType, file))
		        .build();
		okHttpClient.newCall(request).enqueue(new Callback() {
		    @Override
		    public void onFailure(Call call, IOException e) {
		        System.out.println("onFailure: " + e.getMessage());
		    }

		    @Override
		    public void onResponse(Call call, Response response) throws IOException {
		        System.out.println(response.protocol() + " " +response.code() + " " + response.message());
		        Headers headers = response.headers();
		        for (int i = 0; i < headers.size(); i++) {
		            System.out.println(headers.name(i) + ":" + headers.value(i));
		        }
		        System.out.println("onResponse: " + response.body().string());
		    }
		});
	}
	
	
    /**
     * POST方式提交流
     */
	@Test
	public void testOkHttpPostStream() {
		
		String url = "http://127.0.0.1/okhttp/file";
		
		RequestBody requestBody = new RequestBody() {
		    @Nullable
		    @Override
		    public MediaType contentType() {
		        return MediaType.parse("text/x-markdown; charset=utf-8");
		    }

		    @Override
		    public void writeTo(BufferedSink sink) throws IOException {
		        sink.writeUtf8("哈哈哈 ，一个牛逼的流");
		    }
		};

		Request request = new Request.Builder()
		        .url(url)
		        .post(requestBody)
		        .build();
		OkHttpClient okHttpClient = new OkHttpClient();
		okHttpClient.newCall(request).enqueue(new Callback() {
		    @Override
		    public void onFailure(Call call, IOException e) {
		        System.out.println("onFailure: " + e.getMessage());
		    }

		    @Override
		    public void onResponse(Call call, Response response) throws IOException {
		        System.out.println(response.protocol() + " " +response.code() + " " + response.message());
		        Headers headers = response.headers();
		        for (int i = 0; i < headers.size(); i++) {
		            System.out.println(headers.name(i) + ":" + headers.value(i));
		        }
		        System.out.println("onResponse: " + response.body().string());
		    }
		});
	}
}
