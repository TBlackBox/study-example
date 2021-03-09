package sendmail;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class mainTest {

	@Autowired
	JavaMailSenderImpl mailSender;

	//简单邮件测试
	@Test
	public void sendMailTest() {
		SimpleMailMessage message = new SimpleMailMessage();
		//设置邮件的基本信息
		message.setSubject("简单邮件发送测试");
		message.setText("我是测试内容");

		message.setTo("325811402@qq.com");
		message.setFrom("1227900499@qq.com");

		mailSender.send(message);
	}

	//带文件的邮件发送测试
	@Test
	public void sendMailTest2() throws  Exception{
		//1、创建一个复杂的消息邮件
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		//邮件设置
		helper.setSubject("邮件测试");
		//可以加html 标签，单第二个参数要为true ,把html 标签功能开启
		helper.setText("<b style='color:red'>邮件测试类容，可以加标签哦</b>，哈哈",true);

		helper.setTo("325811402@qq.com");
		helper.setFrom("1227900499@qq.com");

		//上传文件
		helper.addAttachment("1.jpg",new File("C:\\Users\\Administrator.BF-20180801KGCC\\Desktop\\头像\\1.jpg"));
		helper.addAttachment("2.jpg",new File("C:\\Users\\Administrator.BF-20180801KGCC\\Desktop\\头像\\2.jpg"));

		mailSender.send(mimeMessage);
	}

}
