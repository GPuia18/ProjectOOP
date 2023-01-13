package com.example.demo.entities;

public class Result {
    public int pts;
    public int difficulty;
    public String name;
    public String difficultyS;

    public Result(int points, int difficulty, String name, String difficultyS) {
        this.pts = points;
        this.difficulty = difficulty;
        this.name = name;
        this.difficultyS = difficultyS;
    }
}
