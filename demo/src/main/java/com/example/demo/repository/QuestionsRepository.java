package com.example.demo.repository;

import com.example.demo.entities.Questions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends CrudRepository<Questions, Integer> {

}
