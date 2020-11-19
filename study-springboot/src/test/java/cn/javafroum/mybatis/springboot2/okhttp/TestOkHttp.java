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
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class TestOkHttp {

	/*
	 * 基本的get请求(同步) 在调用线程接受请求
	 */
	@Test
	public void testOkHttpGet() throws IOException {
		String url = "https://api.cp888.cloud/merchantdata/pull/order?agency=true&endTime=2020-10-30%2011:25:02&lastOrderId=0&merchantAccount=boyu&pageSize=1000&sign=9c75592f053f7b8135b9da1f7daca637&startTime=2020-10-30%2010:55:02";
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
	 * get 同步请求 在子线程接收请求
	 * 
	 */
	@Test
	public void testOkHttpGet1() {
		String url = "http://127.0.0.1/okhttp/get?username=王麻子";
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
		        .url(url)
		        .build();
		final Call call = okHttpClient.newCall(request);
		new Thread(new Runnable() {
		    @Override
		    public void run() {
		        try {
		            Response response = call.execute();
		            System.out.println("run: " + response.body().string());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}).start();
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
	
	/**
	 * POST方式提交分块请求
	 *	MultipartBody 可以构建复杂的请求体，与HTML文件上传形式兼容。
	 *多块请求体中每块请求都是一个请求体，可以定义自己的请求头。
	 *这些请求头可以用来描述这块请求，例如它的 Content-Disposition 。
	 *如果 Content-Length 和 Content-Type 可用的话，他们会被自动添加到请求头中。
	 */
	private static final String IMGUR_CLIENT_ID = "...";
	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
	@Test
	public void postMultipartBody() {
		OkHttpClient client = new OkHttpClient();
	    // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
	    MultipartBody body = new MultipartBody.Builder("AaB03x")
	            .setType(MultipartBody.FORM)
	            .addPart(
	                    Headers.of("Content-Disposition", "form-data; name=\"title\""),
	                    RequestBody.create(null, "Square Logo"))
	            .addPart(
	                    Headers.of("Content-Disposition", "form-data; name=\"image\""),
	                    RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
	            .build();

	    Request request = new Request.Builder()
	            .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
	            .url("https://127.0.0.1/image")
	            .post(body)
	            .build();

	    Call call = client.newCall(request);
	    call.enqueue(new Callback() {
	        @Override
	        public void onFailure(Call call, IOException e) {
	        }

	        @Override
	        public void onResponse(Call call, Response response) throws IOException {
	            System.out.println(response.body().string());

	        }
	    });
	}
}
