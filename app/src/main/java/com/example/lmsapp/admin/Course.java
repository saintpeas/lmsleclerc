package com.example.lmsapp.admin;

public class Course {
    private String title;
    private String description;

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
