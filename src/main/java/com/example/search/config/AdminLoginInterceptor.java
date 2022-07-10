package com.example.search.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AdminLoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("Enter the interceptor...");
        String uri = request.getRequestURI();
        if (uri.startsWith("/admin") && null == request.getSession().getAttribute("loginUser")) {
            request.getSession().setAttribute("login_error_msg", "Please Login First");
            response.sendRedirect(request.getContextPath() + "/admin/login");
            System.out.println("Not logged in, interception successful...");
            return false;
        } else {
            request.getSession().removeAttribute("login_error_msg");
            System.out.println("Logged in, released...");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
