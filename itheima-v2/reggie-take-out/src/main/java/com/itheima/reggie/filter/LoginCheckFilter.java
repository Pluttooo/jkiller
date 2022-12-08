package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验用户是否已登录
 */
@Slf4j
// 拦截所有请求
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        // 定义不需要处理的请求路径
        String[] uris = {
                "/api/**",
                "/api/employee/login",
                "/api/employee/logout",
                "/backend/**",
                "/front/**"
        };
        boolean check = check(uris, requestURI);
        // 写死管理员id
        Long employeeId = 1L;
        if (check) {
            log.info("本次请求{}不需要处理",requestURI);
            BaseContext.setCurrentId(employeeId);
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getSession().getAttribute("employee") != null) {
            // Long employeeId = (Long) request.getSession().getAttribute("employee");
            log.info("用户已登录，用户id为：{}", employeeId);
            BaseContext.setCurrentId(employeeId);
            filterChain.doFilter(request, response);
            return;
        }
        // 未登录时返回未登录结果，通过输出流的方式向客户端响应式页面返回数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(BaseResponse.error("NOTLOGIN")));
    }

    /**
     * 校验uri
     * @param uris
     * @param requestURI
     * @return
     */
    public boolean check(String[] uris, String requestURI) {
        for (String uri : uris) {
            boolean match = PATH_MATCHER.match(uri, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
