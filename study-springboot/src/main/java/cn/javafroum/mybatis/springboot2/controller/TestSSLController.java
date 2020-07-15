package cn.javafroum.mybatis.springboot2.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSSLController {

	@PostMapping(value = "/ssl/{id}",produces=MediaType.ALL_VALUE)
	public void testHttps(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable String id) {
		
		System.out.println("===>id:"+id);
		response.setContentType("text/html;charset=UTF-8");
		try {
		request.setCharacterEncoding("UTF-8");
		
		
		InputStream in = request.getInputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		
		StringBuilder sb =new StringBuilder();
		
		String buffer = null;
		while((buffer = br.readLine()) != null) {
			sb.append(buffer);
		}
		
		String content = sb.toString();
		System.out.println("content:"+content);
		String content1 = content.substring(sb.toString().indexOf("<?xml"), sb.toString().indexOf("</plist>"));
		System.out.println("content1: " + content1);
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.setStatus(301);
		response.setHeader("Location", "https://natapp.javaweb.io/mid.html");
	}
}
