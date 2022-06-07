package ru.otus.spring.domain;

public class Answer {
    private final String text;
    private final boolean correct;

    public Answer(String text, String correct) {
        this.text = text;
        this.correct = Boolean.parseBoolean(correct);
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }
}
