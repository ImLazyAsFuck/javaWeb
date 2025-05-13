package com.example.ss6.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

import java.util.Random;

@WebServlet("/playMiniGameServlet")
public class PlayMiniGameServlet extends HttpServlet{
    private final String[] choices = {"Búa", "Kéo", "Lá"};

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getRequestDispatcher("views/playMinigame.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading page");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int selectedChoice =Integer.parseInt(request.getParameter("playerChoice"));
        String playerChoice = choices[selectedChoice-1];

        if (playerChoice == null) {
            playerChoice = "Chưa chọn";
            request.setAttribute("playerChoice", playerChoice);
            request.setAttribute("computerChoice", "Chưa có");
            request.setAttribute("result", "Vui lòng chọn một tùy chọn hợp lệ");
        } else {

            String computerChoice = choices[new Random().nextInt(choices.length)];

            String result = determineResult(playerChoice, computerChoice);

            request.setAttribute("playerChoice", playerChoice);
            request.setAttribute("computerChoice", computerChoice);
            request.setAttribute("result", result);
        }

        try {
            request.getRequestDispatcher("views/playMinigame.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
    }



    private String determineResult(String player, String computer) {
        if (player.equals(computer)) {
            return "Hòa";
        }
        if ((player.equals("Búa") && computer.equals("Kéo")) ||
                (player.equals("Kéo") && computer.equals("Lá")) ||
                (player.equals("Lá") && computer.equals("Búa"))) {
            return "Bạn thắng!";
        }
        return "Máy thắng!";
    }

}