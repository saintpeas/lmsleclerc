// CourseStudentsActivity.java
package com.example.lmsapp.instructor;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lmsapp.R;
import java.util.ArrayList;
import java.util.List;

public class CourseStudentsActivity extends AppCompatActivity {

    private static String courseName;
    private static List<Student> studentList;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_students);

        courseName = getIntent().getStringExtra("COURSE_NAME");
        TextView title = findViewById(R.id.courseTitle);
        title.setText(courseName);

        RecyclerView recyclerView = findViewById(R.id.studentsRecyclerView);

        if (studentList == null) {
            studentList = getDummyStudents();
        }

        adapter = new StudentAdapter(studentList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public static String getCourseName() {
        return courseName;
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static void updateStudentList(List<Student> updatedList) {
        studentList = updatedList;
    }

    private List<Student> getDummyStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe", "85%", "B+"));
        students.add(new Student("Jane Smith", "92%", "A"));
        students.add(new Student("Robert Johnson", "78%", "C+"));
        students.add(new Student("Emily Davis", "95%", "A+"));
        return students;
    }
}