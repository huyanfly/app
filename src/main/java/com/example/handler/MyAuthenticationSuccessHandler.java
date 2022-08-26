package com.example.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final static  String url="https://www.baidu.com/";
    public static MyAuthenticationSuccessHandler Instance = new MyAuthenticationSuccessHandler(url);

    private MyAuthenticationSuccessHandler(String url) {
    }

    public static MyAuthenticationSuccessHandler getInstance() {
        return Instance;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getAuthorities());
        System.out.println(request.getLocalAddr());
        response.sendRedirect(url);
    }
}
