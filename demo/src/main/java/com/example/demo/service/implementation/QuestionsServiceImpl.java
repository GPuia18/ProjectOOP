package com.example.demo.service.implementation;

import com.example.demo.entities.Questions;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public List<Questions> getQuestion(Integer id) {
        return (List<Questions>) questionsRepository.findById(id).orElse(null);
    }

    public List<Questions> getAllQuestions() {
        return (List<Questions>) questionsRepository.findAll();
    }

    public int[] randomise(int[] a) {
        int k = 0;
        int p;
        int okk = 1;
        while (k < 4) {
            okk = 1;
            p = (int) (Math.random() * (4) + 1);
            for (int i = 0; i < k; i++) {
                if (a[i] == p) okk = 0;
            }
            if (okk == 1) {
                a[k] = p;
                k++;
            }
            //System.out.print(p);
            //System.out.println(a[k]);
        }
        return a;
    }
}
