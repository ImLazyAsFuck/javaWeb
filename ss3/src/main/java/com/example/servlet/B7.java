package com.example.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.service.OrderCalculator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "B7", value = "/b7")
public class B7 extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> displayRows = (List<String>) session.getAttribute("displayRows");
        List<Integer> quantityList = (List<Integer>) session.getAttribute("quantityList");
        List<Double> priceList = (List<Double>) session.getAttribute("priceList");
        
        if (displayRows == null) {
            displayRows = new ArrayList<>();
            quantityList = new ArrayList<>();
            priceList = new ArrayList<>();
        }

        String[] productNames = request.getParameterValues("productName");
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("price");

        if (productNames != null && quantities != null && prices != null) {
            for (int i = 0; i < productNames.length; i++) {
                String name = productNames[i];
                if (name == null || name.trim().isEmpty()) continue;

                try {
                    int qty = Integer.parseInt(quantities[i]);
                    double price = Double.parseDouble(prices[i]);
                    double subtotal = qty * price;

                    displayRows.add("<td>" + name + "</td><td>" + qty + "</td><td>" + price + "</td><td>" + String.format("%.2f", subtotal) + "</td>");
                    quantityList.add(qty);
                    priceList.add(price);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        session.setAttribute("displayRows", displayRows);
        session.setAttribute("quantityList", quantityList);
        session.setAttribute("priceList", priceList);

        OrderCalculator calc = new OrderCalculator();
        double total = calc.calculateTotal(quantityList, priceList);

        request.setAttribute("resultList", displayRows);
        request.setAttribute("total", total);
        request.getRequestDispatcher("b7.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String clearParam = request.getParameter("clear");
        if (clearParam != null && clearParam.equals("true")) {
            session.removeAttribute("displayRows");
            session.removeAttribute("quantityList");
            session.removeAttribute("priceList");
            response.sendRedirect("b7");
            return;
        }

        List<String> displayRows = (List<String>) session.getAttribute("displayRows");
        List<Integer> quantityList = (List<Integer>) session.getAttribute("quantityList");
        List<Double> priceList = (List<Double>) session.getAttribute("priceList");
        
        if (displayRows != null && !displayRows.isEmpty()) {
            OrderCalculator calc = new OrderCalculator();
            double total = calc.calculateTotal(quantityList, priceList);
            
            request.setAttribute("resultList", displayRows);
            request.setAttribute("total", total);
        }
        
        request.getRequestDispatcher("b7.jsp").forward(request, response);
    }
}