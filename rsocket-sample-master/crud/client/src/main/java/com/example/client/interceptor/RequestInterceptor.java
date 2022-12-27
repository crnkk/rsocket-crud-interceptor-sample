package com.example.client.interceptor;

import com.example.client.RsocketClientApplication;
import io.micrometer.core.lang.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class RequestInterceptor implements HandlerInterceptor {

    Logger log= LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    private RsocketClientApplication rsocketClientApplication;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle invoked.....{}:{} " + request.getMethod());
        log.info("Request URL:" + request.getRequestURI() );
        long startTime= System.currentTimeMillis();
        request.setAttribute("startTime Of preHandle :", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,@Nullable ModelAndView modelAndView) throws Exception {
        log.info("In posthandle method is calling");
        String time = (String) request.getAttribute("name");

    }

    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        System.out.println("Request and Response is completed");
    }
}


