package cn.javafroum.mybatis.springboot2.controller.javax;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

	@PostMapping
	public void test(@RequestBody List<User> users) {
	}
	
	@PostMapping
	public void testMap(@RequestBody List<Map<String, String>> maps) {
		
	}
}
