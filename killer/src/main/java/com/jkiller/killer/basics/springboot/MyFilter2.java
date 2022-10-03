package com.jkiller.killer.basics.springboot;

//@Component
//public class MyFilter2 implements Filter {
//
//    private static final Logger logger = LoggerFactory.getLogger(MyFilter2.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.info("初始化过滤器2");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        // 对请求开始预处理
//        logger.info("过滤器开始对请求进行预处理2");
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String requestUri = request.getRequestURI();
//        logger.info("请求的接口为2: " + requestUri);
//        long startTime = System.currentTimeMillis();
//        // 通过 doFilter 方法实现过滤功能
//        filterChain.doFilter(servletRequest, servletResponse);
//        long endTime = System.currentTimeMillis();
//        logger.info("该用户的请求处理完毕, 耗时为2: " + (endTime - startTime));
//    }
//
//    @Override
//    public void destroy() {
//        logger.info("销毁过滤器2");
//    }
//}
