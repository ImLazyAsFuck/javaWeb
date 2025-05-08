package com.example.servlet;

import com.example.model.Seat;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/b9")
public class B9Servlet extends HttpServlet{
    private List<Seat> seats;

    @Override
    public void init() throws ServletException {
        seats = new ArrayList<>();
        for (char row = 'A'; row <= 'E'; row++) {
            for (int num = 1; num <= 10; num++) {
                String code = "" + row + num;
                seats.add(new Seat(code, code, 50000, false));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("seats", seats);
        RequestDispatcher dispatcher = req.getRequestDispatcher("b9.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] chosenIds = req.getParameterValues("selectedSeats");
        List<Seat> chosenList = new ArrayList<>();
        double total = 0;

        if (chosenIds != null) {
            for (String id : chosenIds) {
                for (Seat s : seats) {
                    if (s.getId().equals(id) && !s.isBooked()) {
                        s.setBooked(true);
                        chosenList.add(s);
                        total += s.getPrice();
                        break;
                    }
                }
            }
            req.setAttribute("chosenSeats", chosenList);
            req.setAttribute("total", total);
        } else {
            req.setAttribute("error", "Bạn chưa chọn ghế nào!");
        }

        req.setAttribute("seats", seats);
        RequestDispatcher dispatcher = req.getRequestDispatcher("b9.jsp");
        dispatcher.forward(req, resp);
    }
}
