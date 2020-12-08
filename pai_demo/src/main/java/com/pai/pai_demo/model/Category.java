package com.pai.pai_demo.model;

public enum Category {
    IT("IT"),
    CODING("Programowanie"),
    TESTING("Testowanie oprogramowania"),
    DEVOPS("DEVOPS");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
