package com.ss8.controller;

import com.ss8.model.Question;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
public class QuizController{
    private static final List<Question> QUESTIONS = Arrays.asList(
            new Question(1, "https://i.pinimg.com/736x/ad/d9/0b/add90b0aea307fb2bee6dd8e18fe7686.jpg", "Fate/stay night: Unlimited Blade Works"),
            new Question(2, "https://i.pinimg.com/736x/b6/ed/7a/b6ed7a2faf4e59db0913d9a317d53ebd.jpg", "Re:Zero − Starting Life in Another World"),
            new Question(3, "https://i.pinimg.com/736x/47/01/dc/4701dc4c7a65aac3927ff292e565df3f.jpg", "No Game No Life"),
            new Question(4, "https://i.pinimg.com/736x/41/b6/65/41b665a206b1d02f074d79c03d70fa50.jpg", "Steins;Gate"),
            new Question(5, "https://i.pinimg.com/736x/50/c0/02/50c002890522c7a0674e3b10a5fd2e53.jpg", "Puella Magi Madoka Magica")
    );


    @GetMapping("/quiz")
    public ModelAndView getQuiz(HttpSession session) {
        ModelAndView mv = new ModelAndView("quiz/quiz");
        Question randomQuestion = QUESTIONS.get(new Random().nextInt(QUESTIONS.size()));
        session.setAttribute("currentQuestion", randomQuestion);
        mv.addObject("question", randomQuestion);
        return mv;
    }

    @PostMapping("/guess")
    public ModelAndView guess(@RequestParam("answer") String userAnswer, HttpSession session) {
        ModelAndView mv = new ModelAndView("quiz/quiz");
        Question currentQuestion = (Question) session.getAttribute("currentQuestion");

        if (currentQuestion == null) {
            mv.addObject("message", "Session expired. Please try again.");
            return mv;
        }

        if (currentQuestion.getAnswer().equalsIgnoreCase(userAnswer.trim())) {
            mv.addObject("message", "Correct answer!");
        } else {
            mv.addObject("message", "Wrong answer!");
        }

        mv.addObject("question", currentQuestion);
        return mv;
    }

}
