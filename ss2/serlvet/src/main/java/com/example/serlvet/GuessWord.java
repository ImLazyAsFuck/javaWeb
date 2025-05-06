package com.example.serlvet;

import com.example.model.WordGameModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "GuessWord", urlPatterns = {"/guess-word"})
public class GuessWord extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        WordGameModel gameModel = (WordGameModel) session.getAttribute("gameModel");

        String newGame = request.getParameter("newGame");
        if (gameModel == null || "true".equals(newGame)) {
            gameModel = new WordGameModel();
            session.setAttribute("gameModel", gameModel);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/guessword.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        WordGameModel gameModel = (WordGameModel) session.getAttribute("gameModel");

        if (gameModel == null) {
            gameModel = new WordGameModel();
            session.setAttribute("gameModel", gameModel);
        }

        String guess = request.getParameter("guess");
        if (guess != null && !guess.trim().isEmpty()) {
            gameModel.makeGuess(guess);
        }

        String newGame = request.getParameter("newGame");
        if ("true".equals(newGame)) {
            gameModel.initGame();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/guessword.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
    }
}