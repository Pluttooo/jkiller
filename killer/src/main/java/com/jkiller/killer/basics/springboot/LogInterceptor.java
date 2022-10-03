package com.jkiller.killer.basics.springboot;

//public class LogInterceptor implements HandlerInterceptor {
//
//    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        long startTime = System.currentTimeMillis();
//        logger.info("\\n-------- LogInterception.preHandle --- ");
//        logger.info("Request URL: " + request.getRequestURI());
//        logger.info("Start time: " + startTime);
//        request.setAttribute("startTime", startTime);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.info("\\n-------- LogInterception.postHandle --- ");
//        logger.info("Request URL: " + request.getRequestURI());
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("\\n-------- LogInterception.afterCompletion --- ");
//        long startTime = System.currentTimeMillis();
//        logger.info("Request URL: " + request.getRequestURI());
//        long endTime = System.currentTimeMillis();
//        logger.info("Time taken: " + (endTime - startTime));
//    }
//}
