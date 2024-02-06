package com.anbai.sec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creator: yz
 * Date: 2019/12/14
 */
// 如果使用注解方式请取消@WebServlet注释并注释掉web.xml中TestServlet相关配置，
// 如果和SpringMVC一起配置注解的优先级会较低，导致无法访问，需要修改SpringMVC的Mapping。
//@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);

        request.getParameter("version");
        request.getCookies();
        request.getSession();
        request.getAttribute("attr_1");
        request.getMethod();

        request.getInputStream();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("Hello World~");
        out.flush();
        out.close();

        response.getOutputStream();
        response.setStatus(404);
        response.setHeader("rememberMe", "21312312");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}