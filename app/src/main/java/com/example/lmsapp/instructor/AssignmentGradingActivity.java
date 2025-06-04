package com.example.lmsapp.instructor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lmsapp.R;
import java.util.List;

public class AssignmentGradingActivity extends AppCompatActivity {

    private Student currentStudent;
    private int studentPosition;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_grading);

        String studentName = getIntent().getStringExtra("STUDENT_NAME");
        String courseName = getIntent().getStringExtra("COURSE_NAME");
        studentPosition = getIntent().getIntExtra("POSITION", 0);
        studentList = CourseStudentsActivity.getStudentList();
        currentStudent = studentList.get(studentPosition);

        TextView title = findViewById(R.id.title);
        title.setText("Grade assignments for " + studentName + " in " + courseName);

        setupAssignmentSaving();
    }

    private void setupAssignmentSaving() {
        // Assignment 1
        EditText score1 = findViewById(R.id.score1);
        Button saveButton1 = findViewById(R.id.save_button1);
        score1.setText(String.valueOf(currentStudent.getAssignment1Score()));

        saveButton1.setOnClickListener(v -> {
            try {
                int newScore = Integer.parseInt(score1.getText().toString());
                if (newScore < 0 || newScore > 100) {
                    Toast.makeText(this, "Score must be between 0-100", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentStudent.setAssignment1Score(newScore);
                updateStudentInList();
                Toast.makeText(this, "Assignment 1 grade saved!", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid score format", Toast.LENGTH_SHORT).show();
            }
        });

        // Assignment 2
        EditText score2 = findViewById(R.id.score2);
        Button saveButton2 = findViewById(R.id.save_button2);
        score2.setText(String.valueOf(currentStudent.getAssignment2Score()));

        saveButton2.setOnClickListener(v -> {
            try {
                int newScore = Integer.parseInt(score2.getText().toString());
                if (newScore < 0 || newScore > 100) {
                    Toast.makeText(this, "Score must be between 0-100", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentStudent.setAssignment2Score(newScore);
                updateStudentInList();
                Toast.makeText(this, "Assignment 2 grade saved!", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid score format", Toast.LENGTH_SHORT).show();
            }
        });

        // Assignment 3
        EditText score3 = findViewById(R.id.score3);
        Button saveButton3 = findViewById(R.id.save_button3);
        score3.setText(String.valueOf(currentStudent.getAssignment3Score()));

        saveButton3.setOnClickListener(v -> {
            try {
                int newScore = Integer.parseInt(score3.getText().toString());
                if (newScore < 0 || newScore > 100) {
                    Toast.makeText(this, "Score must be between 0-100", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentStudent.setAssignment3Score(newScore);
                updateStudentInList();
                Toast.makeText(this, "Assignment 3 grade saved!", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid score format", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStudentInList() {
        studentList.set(studentPosition, currentStudent);
        CourseStudentsActivity.updateStudentList(studentList);
    }
}