package com.example.lmsapp.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lmsapp.R;
import com.example.lmsapp.admin.AdminDashboardActivity;
import com.example.lmsapp.instructor.InstructorDashboardActivity;
import com.example.lmsapp.student.StudentDashboardActivity;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsernameOrEmail, etPassword;
    private Button btnLogin, btnGoSignup, btnLoginAsAdmin, btnLoginAsInstructor;
    private Map<String, UserInfo> testUsers;

    private static class UserInfo {
        String password;
        String role;

        UserInfo(String password, String role) {
            this.password = password;
            this.role = role;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
        initializeTestUsers();
        setupLoginButton();
        setupSignupButton();
        setupAdminLoginButton();
        setupInstructorLoginButton();
    }

    private void initializeViews() {
        etUsernameOrEmail = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoSignup = findViewById(R.id.btn_go_signup);
        btnLoginAsAdmin = findViewById(R.id.btnLoginAsAdmin);
        btnLoginAsInstructor = findViewById(R.id.btnLoginAsInstructor);
    }

    private void setupLoginButton() {
        btnLogin.setOnClickListener(v -> {
            String usernameOrEmail = etUsernameOrEmail.getText().toString().trim().toLowerCase();
            String password = etPassword.getText().toString().trim();

            if (usernameOrEmail.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            authenticateUser(usernameOrEmail, password);
        });
    }

    private void setupSignupButton() {
        btnGoSignup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
    }

    private void setupAdminLoginButton() {
        btnLoginAsAdmin.setOnClickListener(v -> {
            autoFillCredentials("admin@lms.com", "password");
            btnLogin.performClick();
        });
    }

    private void setupInstructorLoginButton() {
        btnLoginAsInstructor.setOnClickListener(v -> {
            autoFillCredentials("instructor@lms.com", "password");
            btnLogin.performClick();
        });
    }

    private void autoFillCredentials(String username, String password) {
        etUsernameOrEmail.setText(username);
        etPassword.setText(password);
    }

    private void initializeTestUsers() {
        testUsers = new HashMap<>();

        // Admin users
        testUsers.put("admin", new UserInfo("admin123", "admin"));
        testUsers.put("admin@lms.com", new UserInfo("password", "admin"));
        testUsers.put("administrator", new UserInfo("admin456", "admin"));
        testUsers.put("superuser@lms.com", new UserInfo("super123", "admin"));
        testUsers.put("profdula@gmail.com", new UserInfo("macmac123", "admin"));
        testUsers.put("teacher@lms.com", new UserInfo("teacher123", "admin"));

        // Instructor users
        testUsers.put("instructor", new UserInfo("instructor123", "instructor"));
        testUsers.put("instructor@lms.com", new UserInfo("password", "instructor"));

        // Student users
        testUsers.put("student", new UserInfo("student123", "student"));
        testUsers.put("1", new UserInfo("1", "student"));
        testUsers.put("dula.376141@gmail.com", new UserInfo("macmac123", "student"));
        testUsers.put("student@lms.com", new UserInfo("password", "student"));
        testUsers.put("john.doe@student.com", new UserInfo("john123", "student"));
        testUsers.put("jane.smith@student.com", new UserInfo("jane123", "student"));
        testUsers.put("testuser", new UserInfo("test123", "student"));
        testUsers.put("demo@student.com", new UserInfo("demo123", "student"));
        testUsers.put("guest", new UserInfo("guest123", "student"));
    }

    private void authenticateUser(String input, String password) {
        if (!testUsers.containsKey(input)) {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            return;
        }

        UserInfo user = testUsers.get(input);
        if (!user.password.equals(password)) {
            Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            return;
        }

        navigateToDashboard(user.role, input);
    }

    private void navigateToDashboard(String role, String email) {
        Class<?> targetClass;
        if ("admin".equals(role)) {
            targetClass = AdminDashboardActivity.class;
        } else if ("instructor".equals(role)) {
            targetClass = InstructorDashboardActivity.class;
        } else {
            targetClass = StudentDashboardActivity.class;
        }

        Intent intent = new Intent(LoginActivity.this, targetClass);
        intent.putExtra("USER_EMAIL", email);
        intent.putExtra("USER_ROLE", role);

        String roleName =
                "admin".equals(role) ? "Admin" :
                        "instructor".equals(role) ? "Instructor" : "Student";

        Toast.makeText(this, "Welcome " + roleName + ": " + email, Toast.LENGTH_SHORT).show();

        startActivity(intent);
        finish();
    }
}