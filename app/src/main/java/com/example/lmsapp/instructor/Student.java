// Student.java
package com.example.lmsapp.instructor;

public class Student {
    private String name;
    private String progress;
    private String grade;
    private int assignment1Score;
    private int assignment2Score;
    private int assignment3Score;

    public Student(String name, String progress, String grade) {
        this.name = name;
        this.progress = progress;
        this.grade = grade;
        // Initialize with default scores
        this.assignment1Score = 85;
        this.assignment2Score = 0;
        this.assignment3Score = 0;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getProgress() { return progress; }
    public String getGrade() { return grade; }
    public int getAssignment1Score() { return assignment1Score; }
    public int getAssignment2Score() { return assignment2Score; }
    public int getAssignment3Score() { return assignment3Score; }

    public void setAssignment1Score(int score) {
        this.assignment1Score = score;
        updateProgress();
    }

    public void setAssignment2Score(int score) {
        this.assignment2Score = score;
        updateProgress();
    }

    public void setAssignment3Score(int score) {
        this.assignment3Score = score;
        updateProgress();
    }

    private void updateProgress() {
        int submittedCount = 0;
        int totalScore = 0;

        if (assignment1Score > 0) {
            totalScore += assignment1Score;
            submittedCount++;
        }
        if (assignment2Score > 0) {
            totalScore += assignment2Score;
            submittedCount++;
        }
        if (assignment3Score > 0) {
            totalScore += assignment3Score;
            submittedCount++;
        }

        if (submittedCount > 0) {
            int average = totalScore / submittedCount;
            this.progress = average + "%";
            this.grade = calculateGrade(average);
        }
    }

    private String calculateGrade(int score) {
        if (score >= 95) return "A+";
        else if (score >= 90) return "A";
        else if (score >= 85) return "B+";
        else if (score >= 80) return "B";
        else if (score >= 75) return "C+";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }
}