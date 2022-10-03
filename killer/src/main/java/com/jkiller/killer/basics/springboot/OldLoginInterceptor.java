package com.jkiller.killer.basics.springboot;

/**
 * 如果用户输入已经被废弃的链接 /admin/oldLogin, 它将重定向到新的 /admin/login
 */
//public class OldLoginInterceptor implements HandlerInterceptor {
//
//    private static final Logger logger = LoggerFactory.getLogger(OldLoginInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.info("\\n-------- OldLoginInterceptor.preHandle --- ");
//        logger.info("Request URL: " + request.getRequestURI());
//        logger.info("Sorry! This URL is no longer used, Redirect to /admin/login");
//        logger.info("站点根目录: " + request.getContextPath());
//        response.sendRedirect(request.getContextPath() + "/admin/login");
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.info("\\n-------- OldLoginInterceptor.postHandle --- ");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("\\n-------- QueryStringInterceptor.afterCompletion --- ");
//    }
//}
