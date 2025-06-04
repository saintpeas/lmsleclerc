package com.example.lmsapp.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsapp.R;
import com.example.lmsapp.ui.auth.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class InstructorDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InstructorCourseAdapter adapter;
    private List<String> assignedCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_dashboard);

        recyclerView = findViewById(R.id.instructorCourseRecyclerView);
        assignedCourses = new ArrayList<>();

        // Updated courses
        assignedCourses.add("Introduction to Computing");
        assignedCourses.add("Ethics");
        assignedCourses.add("Entrepreneurship");

        adapter = new InstructorCourseAdapter(assignedCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.instructor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            // Logout logic
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}