package cn.javafroum.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.
                // 使用内存中的 InMemoryUserDetailsManager
                //调用 AuthenticationManagerBuilder#inMemoryAuthentication() 方法，使用内存级别的 InMemoryUserDetailsManager Bean 对象，提供认证的用户信息。
                //    Spring 内置了两种 UserDetailsManager 实现：
                //    InMemoryUserDetailsManager，和「2. 快速入门」是一样的。
                //    JdbcUserDetailsManager ，基于 JDBC的 JdbcUserDetailsManager 。
                //    实际项目中，我们更多采用调用 AuthenticationManagerBuilder#userDetailsService(userDetailsService) 方法，使用自定义实现的 UserDetailsService 实现类，更加灵活且自由的实现认证的用户信息的读取。
                        inMemoryAuthentication()
                // 不使用 PasswordEncoder 密码编码器
                //passwordEncoder(passwordEncoder) 方法，设置 PasswordEncoder 密码编码器。
                //在这里，为了方便，我们使用 NoOpPasswordEncoder 。实际上，等于不使用 PasswordEncoder ，不配置的话会报错。
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                // 配置了「admin/admin」和「normal/normal」两个用户，分别对应 ADMIN 和 NORMAL 角色。相比「2. 快速入门」来说，可以配置更多的用户。
                .withUser("admin").password("admin").roles("ADMIN")
                .and().withUser("normal").password("normal").roles("NORMAL");
    }

    //主要配置 URL 的权限控制。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // <X> 配置请求地址的权限
                .authorizeRequests()
                .antMatchers("/test/all").permitAll() // 所有用户可访问
                .antMatchers("/test/admin").hasRole("ADMIN") // 需要 ADMIN 角色
                .antMatchers("/test/normal").access("hasRole('ROLE_NORMAL')") // 需要 NORMAL 角色。
                // 任何请求，访问的用户都需要经过认证
                .anyRequest().authenticated()
                .and()
                // <Y> 设置 Form 表单登录
                .formLogin()
//                    .loginPage("/login") // 登录 URL 地址
                .permitAll() // 所有用户可访问
                .and()
                // 配置退出相关
                //自定义退出 可以通过 #logoutUrl(String logoutUrl) 方法，来进行设置。不设置使用默认的退出
                .logout()
//                    .logoutUrl("/logout") // 退出 URL 地址
                .permitAll(); // 所有用户可访问
    }

//    调用 HttpSecurity#authorizeRequests() 方法，开始配置 URL 的权限控制。
//            #(String... antPatterns) 方法，配置匹配的 URL 地址，基于 Ant 风格路径表达式 ，可传入多个。
//            【常用】#permitAll() 方法，所有用户可访问。
//            【常用】#denyAll() 方法，所有用户不可访问。
//            【常用】#authenticated() 方法，登录用户可访问。
//            #anonymous() 方法，无需登录，即匿名用户可访问。
//            #rememberMe() 方法，通过 remember me 登录的用户可访问。
//            #fullyAuthenticated() 方法，非 remember me 登录的用户可访问。
//            #hasIpAddress(String ipaddressExpression) 方法，来自指定 IP 表达式的用户可访问。
//            【常用】#hasRole(String role) 方法， 拥有指定角色的用户可访问。
//            【常用】#hasAnyRole(String... roles) 方法，拥有指定任一角色的用户可访问。
//            【常用】#hasAuthority(String authority) 方法，拥有指定权限(authority)的用户可访问。
//            【常用】#hasAuthority(String... authorities) 方法，拥有指定任一权限(authority)的用户可访问。
//            【最牛】#access(String attribute) 方法，当 Spring EL 表达式的执行结果为 true 时，可以访问。
}
