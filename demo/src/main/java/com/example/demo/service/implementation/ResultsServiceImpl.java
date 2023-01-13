package com.example.demo.service.implementation;

import com.example.demo.entities.Results;
import com.example.demo.entities.User;
import com.example.demo.repository.ResultsRepository;
import com.example.demo.service.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultsServiceImpl implements ResultsService {

    @Autowired
    private ResultsRepository resultsRepository;

    @Override
    public void addResult(int score, int difficulty, User user) {
        Results results = new Results();
        results.setScore(score);
        results.setDifficulty(difficulty);
        results.setUser(user);
        resultsRepository.save(results);
    }

    @Override
    public List<Results> getAllResults() {
        return (List<Results>) resultsRepository.findAll();
    }


}
