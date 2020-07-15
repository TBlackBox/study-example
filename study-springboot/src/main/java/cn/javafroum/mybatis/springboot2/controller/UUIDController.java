package cn.javafroum.mybatis.springboot2.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ios/download")
public class UUIDController {
	
	private static final String xml_pre = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\r\n" + 
			"<plist version=\"1.0\">\r\n" + 
			"    <dict>\r\n" + 
			"        <key>PayloadContent</key>\r\n" + 
			"        <dict>\r\n" + 
			"            <key>URL</key>\r\n" + 
			"            <string>https://natapp.javaweb.io/ssl/adex12</string>\r\n" + 
			"            <key>DeviceAttributes</key>\r\n" + 
			"            <array>\r\n" + 
			"                <string>UDID</string>\r\n" + 
			"                <string>IMEI</string>\r\n" + 
			"                <string>ICCID</string>\r\n" + 
			"                <string>VERSION</string>\r\n" + 
			"                <string>PRODUCT</string>\r\n" + 
			"            </array>\r\n" + 
			"        </dict>\r\n" + 
			"        <key>PayloadOrganization</key>\r\n" + 
			"        <string>dev.skyfox.org</string>\r\n" + 
			"        <key>PayloadDisplayName</key>\r\n" + 
			"        <string>查询设备UDID</string>\r\n" + 
			"        <key>PayloadVersion</key>\r\n" + 
			"        <integer>1</integer>\r\n" + 
			"        <key>PayloadUUID</key>\r\n" + 
			"        <string>3C4DC7D2-E475-3375-489C-0BB8D737A653</string>\r\n" + 
			"        <key>PayloadIdentifier</key>\r\n" + 
			"        <string>dev.skyfox.profile-service</string>\r\n" + 
			"        <key>PayloadDescription</key>\r\n" + 
			"        <string>本文件仅用来获取设备ID</string>\r\n" + 
			"        <key>PayloadType</key>\r\n" + 
			"        <string>Profile Service</string>\r\n" + 
			"    </dict>\r\n" + 
			"</plist>";
	

	
	
	@GetMapping
	public void download(HttpServletResponse response,String parentCode) throws IOException {
		response.setHeader("Content-Disposition", "attachment; filename=uuid.mobileconfig");
		response.setContentType("application/x-apple-aspen-config");
		OutputStream os = response.getOutputStream();
		String content = new StringBuilder(xml_pre).toString();
		System.out.println(content);
		os.write(content.getBytes());
		os.flush();
	}
	

}
