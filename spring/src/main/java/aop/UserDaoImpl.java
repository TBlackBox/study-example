package aop;

import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements IUserDao{

	@Override
	public void save() {
		System.out.println("保存用户");
		//throw new RuntimeException("发生运行时异常测试");
	}
}
