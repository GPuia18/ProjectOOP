package com.example.demo.service;

import com.example.demo.entities.Results;
import com.example.demo.entities.User;

import java.util.List;

public interface ResultsService {
    public void addResult(int score, int difficulty, User user);

    public List<Results> getAllResults();
}
