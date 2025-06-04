package com.example.lmsapp.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lmsapp.R;
import java.util.List;

public class PerformanceAdapter extends RecyclerView.Adapter<PerformanceAdapter.ViewHolder> {

    private final List<Course> performanceList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView studentName, performanceData;

        public ViewHolder(View view) {
            super(view);
            studentName = view.findViewById(R.id.courseTitle);
            performanceData = view.findViewById(R.id.courseDescription);
        }
    }

    public PerformanceAdapter(List<Course> performanceList) {
        this.performanceList = performanceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course item = performanceList.get(position);
        holder.studentName.setText(item.getTitle());
        holder.performanceData.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return performanceList.size();
    }
}