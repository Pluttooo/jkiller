package com.jkiller.killer.basics.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("\\n-------- LogInterception.preHandle --- ");
        logger.info("Request URL: " + request.getRequestURI());
        logger.info("Start time: " + startTime);
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("\\n-------- LogInterception.postHandle --- ");
        logger.info("Request URL: " + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("\\n-------- LogInterception.afterCompletion --- ");
        long startTime = System.currentTimeMillis();
        logger.info("Request URL: " + request.getRequestURI());
        long endTime = System.currentTimeMillis();
        logger.info("Time taken: " + (endTime - startTime));
    }
}
