package cn.javafroum.mybatis.springboot2.controller.okhttp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/okhttp")
public class TestOkHttpController {

	/**
	 * get测试
	 * @param username
	 * @return
	 */
	@GetMapping("/get")
	public Object testGet(@RequestParam(value="username")String username) {
		
		try {
			//本地测试  休眠几秒 方便测试性能
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "测试GET成功,用户名：" + username;
	}
	
	/**
	 * pos测试
	 * @param username
	 * @return
	 */
	@PostMapping(value= "/post")
	public Object testPost(@RequestParam(value="username")String username) {
		
		try {
			//本地测试  休眠几秒 方便测试性能
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "测试POST成功,用户名：" + username;
	}
	
	@PostMapping(value= "/file")
	public Object testPostFile(HttpServletRequest request) throws IOException {
		
		String contentType = request.getContentType();
		System.out.println("contentType:" + contentType);
		
		InputStream in = request.getInputStream();
		
		int index;
		byte[] bytes = new byte[1024];
		FileOutputStream downloadFile = new FileOutputStream("D:\\test1.md");
		while ((index = in.read(bytes)) != -1) {
			downloadFile.write(bytes, 0, index);
			downloadFile.flush();
		}
		downloadFile.close();
		in.close();

		return "测试文件上传,文件名：";
	}
}
