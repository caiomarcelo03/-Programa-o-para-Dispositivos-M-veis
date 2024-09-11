package com.example.ac1quiz;

public class Question {
    private String area;
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String area, String question, String[] options, int correctAnswerIndex) {
        this.area = area;
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getArea() {
        return area;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
