package com.example.config;

import com.example.handler.MyAccessDeniedHandler;
import com.example.handler.MyAuthenticationFailureHandler;
import com.example.handler.MyAuthenticationSuccessHandler;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        //打开spring security跨域
        http.cors()
                .and()
                //关闭csrf
                .csrf().disable()
                //关闭session机制
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //放行的请求
                .antMatchers().permitAll()
                //拦截所有请求
                .antMatchers("/**").authenticated()
                .and()
                //将自定义过滤器放到filter链中
                .addFilter()
    }*/
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private DataSource dataSource;
    @Resource
    private PersistentTokenRepository persistentTokenRepository;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //用户自定义用户名与密码属性名字,需和页面form表单中name属性所对应
                .usernameParameter("userName")
                .passwordParameter("passWord")
                //登陆页地址
                .loginProcessingUrl("/login")
                //登陆页面
                .loginPage("/login.html")
                //登陆成功之后的跳转页面,post请求
                .successForwardUrl("/toMain")
                //登陆成功后处理器,不能与successForwardUrl共存
                //.successHandler(MyAuthenticationSuccessHandler.getInstance())
                //登陆失败之后的跳转页面,post请求
                .failureForwardUrl("/toError");
                //登陆失败后处理器
                //.failureHandler(MyAuthenticationFailureHandler.Instance);

        //授权认证
        http.authorizeRequests()
                //login.html不需要认证
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                //通过权限过滤接口
                //.antMatchers("/main1.html").hasAnyAuthority("admin,normal")
                //通过角色过滤接口
                //.antMatchers("/main1.html").hasAnyRole("zhangSan,liSi")
                //通过ip过滤接口
                //.antMatchers("/main1.html").hasIpAddress("192.168.1.102")
                //所有请求都必须被认证,必须登陆之后访问
                .anyRequest().authenticated();
                //.anyRequest().access("@myServiceImpl.hasPermission(request,authentication)");
        //关闭csrf防护
        http.csrf().disable();

        //异常处理
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

        http.rememberMe()
                //自定义登陆逻辑
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository)
                //失效时间单位秒
                .tokenValiditySeconds(60);
                //.rememberMeParameter("");
        http.logout()
                //退出登陆跳转页面
                .logoutSuccessUrl("/login.html");
    }

    @Bean
    public PasswordEncoder getPw(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository getPersistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动建表第一次启动时需要,第二次启动注掉
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}
