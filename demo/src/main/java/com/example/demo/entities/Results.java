package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "results")
@Getter
@Setter
public class Results {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "score")
    public int score;

    @Column(name = "difficulty")
    public int difficulty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public Results() {

    }

    public Results(int id, int score, int difficulty, User user) {
        this.id = id;
        this.score = score;
        this.difficulty = difficulty;
        this.user = user;
    }
}
