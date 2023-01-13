package com.example.demo.service;

import com.example.demo.entities.Questions;

import java.util.List;

public interface QuestionsService {
    public List<Questions> getQuestion(Integer id);

    public List<Questions> getAllQuestions();

    public int[] randomise(int[] a);
}
