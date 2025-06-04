package com.example.lmsapp.instructor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsapp.R;

import java.util.List;

public class InstructorCourseAdapter extends RecyclerView.Adapter<InstructorCourseAdapter.ViewHolder> {

    private final List<String> courses;

    public InstructorCourseAdapter(List<String> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public InstructorCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_instructor_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorCourseAdapter.ViewHolder holder, int position) {
        String course = courses.get(position);
        holder.courseTitle.setText(course);

        holder.viewStudentsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CourseStudentsActivity.class);
            intent.putExtra("COURSE_NAME", course);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle;
        Button viewStudentsBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.instructorCourseTitle);
            viewStudentsBtn = itemView.findViewById(R.id.viewStudentsButton);
        }
    }
}