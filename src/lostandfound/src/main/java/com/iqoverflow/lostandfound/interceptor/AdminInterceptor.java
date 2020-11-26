package com.iqoverflow.lostandfound.interceptor;

import com.iqoverflow.lostandfound.listener.MySessionContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    public static HttpSession session = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        try{
            Cookie[] cookies = request.getCookies();

  /*      if(cookies == null){
            //session = request.getSession();
            System.out.println("没有cookie！");
            response.sendRedirect("/error/loginError");

            return false;
        }*/

            for(Cookie cookie:cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    System.out.println("获取了sessionid!");
                    session = MySessionContext.getSession(cookie.getValue());
                }
            }
        }catch (Exception e){
            System.out.println("没有cookie！");
        }


        if(session == null){
            session = request.getSession();
            System.out.println("没有session！");
        }else {
            System.out.println("有session！"+ session);
        }

        if(session.getAttribute("openid") == null){
            response.sendRedirect("/error/loginError");
            return false;
        }

        return true;

    }
}
