package com.example.demo.controller;

import com.example.demo.entities.Questions;
import com.example.demo.entities.Result;
import com.example.demo.entities.Results;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.QuestionsService;
import com.example.demo.service.ResultsService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResultsService resultsService;

    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/login")
    public String log(HttpServletRequest request) {
        return "login.html";
    }

    int id;

    @RequestMapping("/login/enter")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);
        if (user != null) {
            id = user.getId();
            response.sendRedirect("/user/choose");
        } else {
            response.sendRedirect("/user/invalid");
        }
    }

    @RequestMapping("/enter")
    public String enter(HttpServletRequest request) {
        return "signup.html";
    }

    @RequestMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int ok = 0;
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',', '(', ')', '[', ']', '{', '}', '|', '\'', '`', '"'};
        for (int i = 0; i < firstName.length(); i++) {
            char c = firstName.charAt(i);
            for (int j = 0; j < ch.length; j++) {
                if (c == ch[j]) {
                    ok = 1;
                }
            }
        }
        if (lastName.isBlank() == Boolean.FALSE) {
            for (int i = 0; i < lastName.length(); i++) {
                char c = lastName.charAt(i);
                for (int j = 0; j < ch.length; j++) {
                    if (c == ch[j]) {
                        ok = 1;
                    }
                }
            }
        }
        if (ok == 1) {
            response.sendRedirect("/user/invalid");
        } else if (firstName.isBlank() == Boolean.TRUE || username.isEmpty() == Boolean.TRUE || password.isEmpty()) {
            response.sendRedirect("/user/invalid");
        } else {
            userService.addUser(firstName, lastName, username, password);
            response.sendRedirect("/user/login");
        }
    }

    @RequestMapping("/invalid")
    public String invalid() {
        return "invalid.html";
    }

    @RequestMapping("/choose")
    public String choseGame(HttpServletRequest request) {
        return "choose.html";
    }

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        ModelAndView mv = new ModelAndView("list-users.html");
        mv.addObject("myUsers", users);
        return mv;
    }

    String points;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/result")
    public void addResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        points = request.getParameter("points");
        String difficulty = request.getParameter("difficulty");
        int dif = 0;
        for (int i = 1; i <= 3; i++) {
            if (difficulty.equals(String.valueOf(i))) {
                dif = i;
            }
        }
        int myPoints = 0;
        for (int i = 1; i <= 10; i++) {
            if (points.equals(String.valueOf(i))) {
                myPoints = i;
            }
        }
        User user = userService.getUserById(id);
        resultsService.addResult(myPoints, dif, user);
        if (dif == 1) {
            response.sendRedirect("/user/ranking/easy");
        } else if (dif == 2) {
            response.sendRedirect("/user/ranking/medium");
        } else {
            response.sendRedirect("/user/ranking/hard");
        }
    }

    @RequestMapping("/rankings")
    public String getRankings() {
        return "rankings.html";
    }

    @RequestMapping("/ranking/easy")
    public ModelAndView showResultsE(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("ranking.html");
        User user;
        List<Results> list = resultsService.getAllResults();
        List<Result> listR = new ArrayList<Result>();
        Result difRes = null;
        for (int i = 0; i < list.size(); i++) {
            Results res = list.get(i);
            User userId = res.getUser();
            user = userService.getUserById(userId.getId());
            int diff = res.getDifficulty();
            if (diff == 1) {
                Result newRes = new Result(res.getScore(), diff, user.getFirstName() + " " + user.getLastName(), "Easy");
                listR.add(newRes);
                difRes = new Result(0, 0, "", "Easy");
            }
        }
        mv.addObject("myDifficulty", difRes);
        mv.addObject("myResults", listR);
        return mv;
    }

    @RequestMapping("/ranking/medium")
    public ModelAndView showResultsM(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("ranking.html");
        User user;
        List<Results> list = resultsService.getAllResults();
        List<Result> listR = new ArrayList<Result>();
        Result difRes = null;
        for (int i = 0; i < list.size(); i++) {
            Results res = list.get(i);
            User userId = res.getUser();
            user = userService.getUserById(userId.getId());
            int diff = res.getDifficulty();
            if (diff == 2) {
                Result newRes = new Result(res.getScore(), diff, user.getFirstName() + " " + user.getLastName(), "Medium");
                listR.add(newRes);
                difRes = new Result(0, 0, "", "Medium");
            }
        }
        mv.addObject("myDifficulty", difRes);
        mv.addObject("myResults", listR);
        return mv;
    }

    @RequestMapping("/ranking/hard")
    public ModelAndView showResultsH(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("ranking.html");
        User user;
        List<Results> list = resultsService.getAllResults();
        List<Result> listR = new ArrayList<Result>();
        Result difRes = null;
        for (int i = 0; i < list.size(); i++) {
            Results res = list.get(i);
            User userId = res.getUser();
            user = userService.getUserById(userId.getId());
            int diff = res.getDifficulty();
            if (diff == 3) {
                Result newRes = new Result(res.getScore(), diff, user.getFirstName() + " " + user.getLastName(), "Hard");
                listR.add(newRes);
                difRes = new Result(0, 0, "", "Hard");
            }
        }
        mv.addObject("myDifficulty", difRes);
        mv.addObject("myResults", listR);
        return mv;
    }
}
