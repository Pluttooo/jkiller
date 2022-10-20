package com.itheima.web;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * servlet快速入门
 * 实现servlet接口时 要重写接口中的全部方法
 * 使用 @WebServlet注解 来配置该Servlet的访问路径
 */
@WebServlet(value = "/servlet/demo1")
public class ServletDemo1 implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service方法比较重要
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet hello world");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
