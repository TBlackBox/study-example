package cn.javafroum.mybatis.springboot2.util;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class OkHttpUtil{
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);
    
    private static OkHttpClient  okHttpClient;

    @Autowired
    public OkHttpUtil(OkHttpClient  okHttpClient) {
        OkHttpUtil.okHttpClient= okHttpClient;
    } 
    
    /**
              * 无参get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static  String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try { 
           response = okHttpClient.newCall(request).execute();
//           int status = response.code();
           if (response.isSuccessful()) {
               return response.body().string();
           }
        }finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }
    
    /**
     * get
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据
     * @return
     */
    public static String get(String url, Map<String, String> queries) throws IOException  {
        StringBuffer sb = new StringBuffer(url);
        boolean firstFlag = true;
        for (String k : queries.keySet()) {
        	if (firstFlag) {
                sb.append("?" + k + "=" + queries.get(k));
                firstFlag = false;
            } else {
                sb.append("&" + k + "=" + queries.get(k));
            }
		}

        Request request = new Request.Builder()
                .url(sb.toString())
                .build();
        Response response = null;
        try { 
            response = okHttpClient.newCall(request).execute();
//            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        }finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

    
    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String post(String url, Map<String, String> params) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = null;
        try { 
            response = okHttpClient.newCall(request).execute();
//            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}");
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

    /**
     * get
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String getForHeader(String url, Map<String, String> queries) {
        StringBuffer sb = new StringBuffer(url);
        queries.forEach((k,v) -> {
        	boolean firstFlag = true;
        	if (firstFlag) {
                sb.append("?" + k + "=" + v);
                firstFlag = false;
            } else {
                sb.append("&" + k + "=" + v);
            }
        });
        
        Request request = new Request.Builder()
                .addHeader("key", "value")
                .url(sb.toString())
                .build();
        Response response = null;
        try { 
            response = okHttpClient.newCall(request).execute();
//            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}");
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
	     * 参数一：请求Url
	     * 参数二：请求的JSON
	     * 参数三：请求回调
     */
    public static String postJsonParams(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try { 
            response = okHttpClient.newCall(request).execute();
//            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}");
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

    /**
     * Post请求发送xml数据....
	     * 参数一：请求Url
	     * 参数二：请求的xmlString
	     * 参数三：请求回调
     */
    public static String postXmlParams(String url, String xml) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try { 
            response = okHttpClient.newCall(request).execute();
//            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}");
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }
}