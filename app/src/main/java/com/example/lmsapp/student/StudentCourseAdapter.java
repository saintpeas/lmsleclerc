package com.example.lmsapp.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsapp.R;
import com.example.lmsapp.student.StudentDashboardActivity.Course;

import java.util.List;

public class StudentCourseAdapter extends RecyclerView.Adapter<StudentCourseAdapter.ViewHolder> {

    private final List<Course> courseList;

    public StudentCourseAdapter(List<Course> courses) {
        this.courseList = courses;
    }

    @NonNull
    @Override
    public StudentCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentCourseAdapter.ViewHolder holder, int position) {
        Course course = courseList.get(position);

        // Set course information
        holder.courseTitle.setText(course.title);
        holder.courseGrade.setText("Grade: " + course.grade);
        holder.assignmentStatus.setText("Assignments: " + course.assignments);

        // Set progress information
        holder.courseProgressBar.setProgress(course.progress);
        holder.courseProgressText.setText(course.progress + "%");
        holder.courseProgressLabel.setText(course.progress + "% Complete");
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle, courseGrade, assignmentStatus;
        ProgressBar courseProgressBar;
        TextView courseProgressText, courseProgressLabel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.studentCourseTitle);
            courseGrade = itemView.findViewById(R.id.courseGrade);
            assignmentStatus = itemView.findViewById(R.id.assignmentStatus);

            // Progress views
            courseProgressBar = itemView.findViewById(R.id.courseProgressBar);
            courseProgressText = itemView.findViewById(R.id.courseProgressText);
            courseProgressLabel = itemView.findViewById(R.id.courseProgressLabel);
        }
    }
}