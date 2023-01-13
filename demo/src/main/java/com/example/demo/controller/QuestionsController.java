package com.example.demo.controller;

import com.example.demo.entities.Questions;
import com.example.demo.entities.Result;
import com.example.demo.entities.User;
import com.example.demo.service.QuestionsService;
import com.example.demo.service.ResultsService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/game")
public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private ResultsService resultsService;

    @Autowired
    private UserService userService;

    String answer1, answer2, answer3, answer4;
    String goodAnswer;

    String goodAnswerId;
    String answer;

    List<Questions> questions;
    Questions que;

    int dif;

    int points = 0;

    int qnr = 0;

    @RequestMapping("/easy")
    public void easy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        dif = 1;
        response.sendRedirect("/game/q/0");
    }

    @RequestMapping("/medium")
    public void medium(HttpServletResponse response) throws IOException {
        dif = 2;
        response.sendRedirect("/game/q/0");
    }

    @RequestMapping("/hard")
    public void hard(HttpServletResponse response) throws IOException {
        dif = 3;
        response.sendRedirect("/game/q/0");
    }

    @RequestMapping("/q/0")
    public ModelAndView getQuestion0(HttpServletRequest request, HttpServletResponse response) {
        qnr = 0;
        points = 0;
        questions = questionsService.getAllQuestions();
        for (int i = 0; i < 10; i++) {
            if (qnr == i) {
                que = questions.get(i + 10 * (dif - 1));
            }
        }
        int[] a = new int[20];
        questionsService.randomise(a);
        answer1 = que.getAnswer1();
        answer2 = que.getAnswer2();
        answer3 = que.getAnswer3();
        answer4 = que.getAnswer4();
        goodAnswer = answer1;

        for (int i = 0; i < 4; i++) {
            if (a[i] == 1) {
                if (i == 0) {
                    que.setAnswer1(answer1);
                    goodAnswerId = "1";
                } else if (i == 1) {
                    que.setAnswer1(answer2);
                } else if (i == 2) {
                    que.setAnswer1(answer3);
                } else {
                    que.setAnswer1(answer4);
                }
            } else if (a[i] == 2) {
                if (i == 0) {
                    que.setAnswer2(answer1);
                    goodAnswerId = "2";
                } else if (i == 1) {
                    que.setAnswer2(answer2);
                } else if (i == 2) {
                    que.setAnswer2(answer3);
                } else {
                    que.setAnswer2(answer4);
                }
            } else if (a[i] == 3) {
                if (i == 0) {
                    que.setAnswer3(answer1);
                    goodAnswerId = "3";
                } else if (i == 1) {
                    que.setAnswer3(answer2);
                } else if (i == 2) {
                    que.setAnswer3(answer3);
                } else {
                    que.setAnswer3(answer4);
                }
            } else {
                if (i == 0) {
                    que.setAnswer4(answer1);
                    goodAnswerId = "4";
                } else if (i == 1) {
                    que.setAnswer4(answer2);
                } else if (i == 2) {
                    que.setAnswer4(answer3);
                } else {
                    que.setAnswer4(answer4);
                }
            }
        }
        ModelAndView mv = new ModelAndView("game.html");
        mv.addObject("myQuestions", que);
        return mv;
    }

    @RequestMapping(value = {"/q/1", "/q/2", "/q/3", "/q/4", "/q/5", "/q/6", "/q/7", "/q/8", "/q/9"})
    public ModelAndView getQuestion(HttpServletRequest request, HttpServletResponse response) {
        for (int i = 0; i < 10; i++) {
            if (qnr == i) {
                que = questions.get(i + 10 * (dif - 1));
            }
        }
        int[] a = new int[20];
        questionsService.randomise(a);
        answer1 = que.getAnswer1();
        answer2 = que.getAnswer2();
        answer3 = que.getAnswer3();
        answer4 = que.getAnswer4();
        goodAnswer = answer1;

        for (int i = 0; i < 4; i++) {
            if (a[i] == 1) {
                if (i == 0) {
                    que.setAnswer1(answer1);
                    goodAnswerId = "1";
                } else if (i == 1) {
                    que.setAnswer1(answer2);
                } else if (i == 2) {
                    que.setAnswer1(answer3);
                } else {
                    que.setAnswer1(answer4);
                }
            } else if (a[i] == 2) {
                if (i == 0) {
                    que.setAnswer2(answer1);
                    goodAnswerId = "2";
                } else if (i == 1) {
                    que.setAnswer2(answer2);
                } else if (i == 2) {
                    que.setAnswer2(answer3);
                } else {
                    que.setAnswer2(answer4);
                }
            } else if (a[i] == 3) {
                if (i == 0) {
                    que.setAnswer3(answer1);
                    goodAnswerId = "3";
                } else if (i == 1) {
                    que.setAnswer3(answer2);
                } else if (i == 2) {
                    que.setAnswer3(answer3);
                } else {
                    que.setAnswer3(answer4);
                }
            } else {
                if (i == 0) {
                    que.setAnswer4(answer1);
                    goodAnswerId = "4";
                } else if (i == 1) {
                    que.setAnswer4(answer2);
                } else if (i == 2) {
                    que.setAnswer4(answer3);
                } else {
                    que.setAnswer4(answer4);
                }
            }
        }
        ModelAndView mv = new ModelAndView("game.html");
        mv.addObject("myQuestions", que);
        return mv;
    }

    @RequestMapping("/answered")
    public void getAnswer0(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        answer = request.getParameter("answer");
        if (answer.equals(goodAnswerId)) {
            //ok=1;
            model.addAttribute("correct", Boolean.TRUE);
            points++;
        } else {
            //ok=0;
            model.addAttribute("correct", Boolean.FALSE);
        }
        if (qnr == 0) {
            response.sendRedirect("/game/answered/q/0");
        } else if (qnr == 1) {
            response.sendRedirect("/game/answered/q/1");
        } else if (qnr == 2) {
            response.sendRedirect("/game/answered/q/2");
        } else if (qnr == 3) {
            response.sendRedirect("/game/answered/q/3");
        } else if (qnr == 4) {
            response.sendRedirect("/game/answered/q/4");
        } else if (qnr == 5) {
            response.sendRedirect("/game/answered/q/5");
        } else if (qnr == 6) {
            response.sendRedirect("/game/answered/q/6");
        } else if (qnr == 7) {
            response.sendRedirect("/game/answered/q/7");
        } else if (qnr == 8) {
            response.sendRedirect("/game/answered/q/8");
        } else if (qnr == 9) {
            response.sendRedirect("/game/answered/q/9");
        } else {
            response.sendRedirect("/game/result");
        }
    }

    @RequestMapping(value = {"/answered/q/0", "/answered/q/1", "/answered/q/2", "/answered/q/3", "/answered/q/4", "/answered/q/5", "/answered/q/6", "/answered/q/7", "/answered/q/8", "/answered/q/9"})
    public ModelAndView showAnswer0(HttpServletRequest request, HttpServletResponse response, Model model) {
        request.getParameter("answer");
        if (goodAnswerId.equals(answer)) {
            model.addAttribute("correct", Boolean.TRUE);
        } else {
            model.addAttribute("correct", Boolean.FALSE);
        }
        for (int i = 0; i < 10; i++) {
            if (qnr == i) {
                que = questions.get(i + 10 * (dif - 1));
            }
        }
        int id = que.getId();
        id = id - 10 * dif + 1;
        que.setId(id);
        qnr++;
        ModelAndView mv = new ModelAndView("game-answered.html");
        mv.addObject("myQuestions", que);
        return mv;
    }

    @RequestMapping("/q/10")
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/game/result");
    }

    @RequestMapping("/result")
    public ModelAndView showResult(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("result.html");
        Result result = new Result(points, dif, "", "");
        mv.addObject("myResult", result);
        return mv;
    }
}
