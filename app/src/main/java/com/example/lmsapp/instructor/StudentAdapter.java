// StudentAdapter.java
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

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private final List<Student> students;
    private final Context context;

    public StudentAdapter(List<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = students.get(position);
        holder.studentName.setText(student.getName());
        holder.progress.setText(student.getProgress());
        holder.grade.setText(student.getGrade());

        holder.gradeAssignmentsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, AssignmentGradingActivity.class);
            intent.putExtra("STUDENT_NAME", student.getName());
            intent.putExtra("COURSE_NAME", CourseStudentsActivity.getCourseName());
            intent.putExtra("POSITION", position); // Pass position
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName, progress, grade;
        Button gradeAssignmentsBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            progress = itemView.findViewById(R.id.progressText);
            grade = itemView.findViewById(R.id.gradeText);
            gradeAssignmentsBtn = itemView.findViewById(R.id.gradeAssignmentsButton);
        }
    }
}