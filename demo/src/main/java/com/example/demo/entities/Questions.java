package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Questions {
    @Id
    @Column(name = "id_question")
    public Integer id;
    @Column(name = "question_text")
    public String questionText;
    @Column(name = "answer1")
    public String answer1;
    @Column(name = "answer2")
    public String answer2;
    @Column(name = "answer3")
    public String answer3;
    @Column(name = "answer4")
    public String answer4;
    @Column(name = "difficulty")
    public Integer difficulty;
}