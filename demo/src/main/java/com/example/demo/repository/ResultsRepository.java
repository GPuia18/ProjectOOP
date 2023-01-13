package com.example.demo.repository;

import com.example.demo.entities.Result;
import com.example.demo.entities.Results;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends CrudRepository<Results, Integer> {
}
