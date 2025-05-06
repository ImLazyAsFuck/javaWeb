package com.example.serlvet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LifecycleServlet", value = "/life-cycle-servlet")
public class LifecycleServlet extends HttpServlet{

    public void init() throws ServletException{
        super.init();
        System.out.println("init() được gọi: Servlet được khởi tạo");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Servlet Lifecycle</title></head><body>");
        out.println("<h1>Lifecycle of a Servlet</h1>");
        out.println("<h2>1. init():</h2><p>Được gọi khi servlet được khởi tạo lần đầu tiên.</p>");
        out.println("<h2>2. service():</h2><p>Xử lý các request (ở đây là doGet được gọi từ service()).</p>");
        out.println("<h2>3. destroy():</h2><p>Được gọi khi servlet bị gỡ khỏi container.</p>");
        out.println("<a href=" + "/" + ">Back</a>");
        out.println("</body></html>");
    }

    public void destroy() {
        System.out.println("destroy() được gọi: Servlet bị huỷ");
        super.destroy();
    }
}