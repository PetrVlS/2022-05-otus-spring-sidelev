package ru.otus.spring.domain;

public class Answer {
    private final String text;
    private final boolean isTrueAnswer;

    public Answer(String text, String isTrueAnswer) {
        this.text = text;
        this.isTrueAnswer = Boolean.parseBoolean(isTrueAnswer);
    }

    public String getText() {
        return text;
    }
}
