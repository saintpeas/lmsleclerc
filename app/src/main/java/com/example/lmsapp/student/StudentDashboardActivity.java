package com.example.lmsapp.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsapp.R;
import com.example.lmsapp.ui.auth.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StudentDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentCourseAdapter adapter;
    private List<Course> enrolledCourses;
    private LinearLayout deadlineContainer;
    private ProgressBar overallProgressBar;
    private TextView progressPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        // Initialize UI components
        recyclerView = findViewById(R.id.studentCourseRecyclerView);
        Button btnLogout = findViewById(R.id.btnLogout);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        deadlineContainer = findViewById(R.id.deadlineContainer);
        overallProgressBar = findViewById(R.id.overallProgressBar);
        progressPercentage = findViewById(R.id.progressPercentage);

        // Initialize enrolledCourses BEFORE setting up progress tracking
        enrolledCourses = new ArrayList<>();
        enrolledCourses.add(new Course("Mathematics 101", "A", "2 Submitted", 75));
        enrolledCourses.add(new Course("Introduction to Programming", "B+", "3 Submitted", 60));
        enrolledCourses.add(new Course("English Composition", "A-", "1 Pending", 40));

        // Setup RecyclerView
        adapter = new StudentCourseAdapter(enrolledCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup progress tracking AFTER initializing enrolledCourses
        setupProgressTracking();

        // Setup deadline notifications
        setupDeadlineNotifications();

        // Setup Bottom Navigation
        bottomNav.setSelectedItemId(R.id.nav_student);
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_admin) {
                Toast.makeText(this, "Access Denied: Students can't access Admin dashboard",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
            return item.getItemId() == R.id.nav_student;
        });

        // Setup Logout Button
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(StudentDashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void setupProgressTracking() {
        // Calculate overall progress (average of all courses)
        int totalProgress = 0;
        for (Course course : enrolledCourses) {
            totalProgress += course.progress;
        }
        int averageProgress = enrolledCourses.isEmpty() ? 0 : totalProgress / enrolledCourses.size();

        overallProgressBar.setProgress(averageProgress);
        progressPercentage.setText(averageProgress + "% Complete");
    }

    private void setupDeadlineNotifications() {
        // Sample deadlines data
        List<Deadline> deadlines = new ArrayList<>();
        deadlines.add(new Deadline("Mathematics Assignment", "Mathematics 101", "Due: Tomorrow"));
        deadlines.add(new Deadline("Programming Project", "Introduction to Programming", "Due: 3 days"));
        deadlines.add(new Deadline("Essay Submission", "English Composition", "Due: 5 days"));

        // Add deadlines to container
        LayoutInflater inflater = LayoutInflater.from(this);
        for (Deadline deadline : deadlines) {
            View deadlineView = inflater.inflate(R.layout.item_deadline, deadlineContainer, false);

            TextView title = deadlineView.findViewById(R.id.deadlineTitle);
            TextView course = deadlineView.findViewById(R.id.deadlineCourse);
            TextView time = deadlineView.findViewById(R.id.deadlineTime);

            title.setText(deadline.title);
            course.setText(deadline.course);
            time.setText(deadline.time);

            deadlineContainer.addView(deadlineView);
        }
    }

    // Inner class to represent a Course
    public static class Course {
        public String title;
        public String grade;
        public String assignments;
        public int progress; // Progress percentage

        public Course(String title, String grade, String assignments, int progress) {
            this.title = title;
            this.grade = grade;
            this.assignments = assignments;
            this.progress = progress;
        }
    }

    // Inner class to represent a Deadline
    public static class Deadline {
        public String title;
        public String course;
        public String time;

        public Deadline(String title, String course, String time) {
            this.title = title;
            this.course = course;
            this.time = time;
        }
    }
}