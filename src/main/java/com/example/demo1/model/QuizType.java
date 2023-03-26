package com.example.demo1.model;

public enum QuizType {
    RANDOM("random"),
    SEARCHED_WORD("searchedword");

    private final String path;

    QuizType(String path) {
        this.path = path;
    }

    public static QuizType of(String path) {
        for(QuizType type : QuizType.values()) {
            if (type.getPath().equals(path)) {
                return type;
            }
        }
        return null;
    }

    public String getPath() {
        return path;
    }
}
