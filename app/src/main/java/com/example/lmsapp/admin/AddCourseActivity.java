package com.example.lmsapp.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lmsapp.R;

public class AddCourseActivity extends AppCompatActivity {

    private EditText titleInput, descriptionInput;
    private Button saveCourseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        saveCourseBtn = findViewById(R.id.saveCourseBtn);

        saveCourseBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String desc = descriptionInput.getText().toString();

            Intent result = new Intent();
            result.putExtra("title", title);
            result.putExtra("description", desc);
            setResult(Activity.RESULT_OK, result);
            finish();
        });
    }
}
