package com.mystorage.servlet;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "b1", value = "/b1")
public class B1 extends HttpServlet{
    private String message;

    public void init(){
        message =  "          +-------------------+<br/>" +
                "          |     Client        |  ⇨  Trình duyệt người dùng<br/>" +
                "          +--------+----------+<br/>" +
                "                   |<br/>" +
                "                   v<br/>" +
                "          +--------+----------+<br/>" +
                "          |     Web Server    | ⇨ Nhận HTTP request, phân phối tài nguyên tĩnh (HTML, CSS, JS)<br/>" +
                "          +--------+----------+<br/>" +
                "                   |<br/>" +
                "                   v<br/>" +
                "          +--------+----------+<br/>" +
                "          | Application Server| ⇨ Xử lý logic nghiệp vụ (Spring Boot, Jakarta EE, v.v.)<br/>" +
                "          +--------+----------+<br/>" +
                "                   |<br/>" +
                "                   v<br/>" +
                "          +--------+----------+<br/>" +
                "          |     Database      | ⇨ Lưu trữ dữ liệu (MySQL, PostgreSQL,...)<br/>" +
                "          +-------------------+<br/>";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>" + message + "</h1>");
        out.println("<a href=" + "/myStorage" + ">Back</a>");
        out.println("</body></html>");
    }

    public void destroy(){
    }
}