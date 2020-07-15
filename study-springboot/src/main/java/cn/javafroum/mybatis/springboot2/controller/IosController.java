package cn.javafroum.mybatis.springboot2.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/op/download")
public class IosController {
	
	private static final String xml_pre = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\r\n" + 
			"<plist version=\"1.0\">\r\n" + 
			"<dict>\r\n" + 
			"	<key>PayloadContent</key>\r\n" + 
			"	<array>\r\n" + 
			"		<dict>\r\n" + 
			"			<key>FullScreen</key>\r\n" + 
			"			<false/>\r\n" + 
			"			<key>IsRemovable</key>\r\n" + 
			"			<true/>\r\n" + 
			"			<key>Label</key>\r\n" + 
			"			<string>测试</string>\r\n" + 
			"			<key>PayloadDescription</key>\r\n" + 
			"			<string>配置 Web Clip 设置</string>\r\n" + 
			"			<key>PayloadDisplayName</key>\r\n" + 
			"			<string>Web Clip</string>\r\n" + 
			"			<key>PayloadIdentifier</key>\r\n" + 
			"			<string>com.apple.webClip.managed.3BD2191F-DB6B-47D6-91E6-C06BAFCC9895</string>\r\n" + 
			"			<key>PayloadType</key>\r\n" + 
			"			<string>com.apple.webClip.managed</string>\r\n" + 
			"			<key>PayloadUUID</key>\r\n" + 
			"			<string>3BD2191F-DB6B-47D6-91E6-C06BAFCC9895</string>\r\n" + 
			"			<key>PayloadVersion</key>\r\n" + 
			"			<integer>1</integer>\r\n" + 
			"			<key>Precomposed</key>\r\n" + 
			"			<false/>\r\n" + 
			"			<key>URL</key>\r\n" + 
			"			<string>";
	
	private static final String xml_end = "</string>\r\n" + 
			"		</dict>\r\n" + 
			"	</array>\r\n" + 
			"	<key>PayloadDisplayName</key>\r\n" + 
			"	<string>测试</string>\r\n" + 
			"	<key>PayloadIdentifier</key>\r\n" + 
			"	<string>muronghengdeMacBook-Pro.D26F000A-A2CF-428C-A5A7-CD06646CECF8</string>\r\n" + 
			"	<key>PayloadRemovalDisallowed</key>\r\n" + 
			"	<false/>\r\n" + 
			"	<key>PayloadType</key>\r\n" + 
			"	<string>Configuration</string>\r\n" + 
			"	<key>PayloadUUID</key>\r\n" + 
			"	<string>6F6E8284-FAFA-4949-8E2B-45C82091DACE</string>\r\n" + 
			"	<key>PayloadVersion</key>\r\n" + 
			"	<integer>1</integer>\r\n" + 
			"</dict>\r\n" + 
			"</plist>";

	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public Object download(HttpServletResponse response,String parentCode) throws IOException {
		response.setHeader("Content-Disposition", "attachment; filename=ios.mobileconfig");
		OutputStream os = response.getOutputStream();
		String content = new StringBuilder(xml_pre).append("http://192.168.0.180/static/test.html?parentCode=").append(parentCode).append(xml_end).toString();
		System.out.println(content);
		os.write(content.getBytes());
		os.flush();
		return parentCode;
	}
	

}
