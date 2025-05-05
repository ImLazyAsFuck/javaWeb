package com.mystorage.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegisterTicket")
public class RegisterTicket extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRegister(request, response);
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String className = request.getParameter("className");
        String vehicleType = request.getParameter("vehicleType");
        String licensePlate = request.getParameter("licensePlate");

        try {

            if (fullName == null || fullName.trim().isEmpty() ||
                    className == null || className.trim().isEmpty() ||
                    vehicleType == null || vehicleType.trim().isEmpty() ||
                    licensePlate == null || licensePlate.trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ thông tin!");
            }

            if (!licensePlate.matches("[0-9A-Z-]{6,10}")) {
                throw new IllegalArgumentException("Biển số xe không hợp lệ!");
            }


            request.setAttribute("message", "Đăng ký vé xe thành công cho " + fullName + "!");
            request.setAttribute("status", "success");

        } catch (IllegalArgumentException e) {

            request.setAttribute("message", "Đăng ký thất bại: " + e.getMessage());
            request.setAttribute("status", "error");
        }

        request.getRequestDispatcher("/resultRegister.jsp").forward(request, response);
    }
}