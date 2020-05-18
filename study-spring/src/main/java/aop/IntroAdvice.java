package aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

@Component
public class IntroAdvice extends DelegatingIntroductionInterceptor implements IAdminDao{

	 @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }
	
	@Override
	public void delete() {
		System.out.println("管理员删除用户");
	}

}
