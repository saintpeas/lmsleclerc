package com.example.lmsapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.lmsapp.R;
import com.example.lmsapp.ui.auth.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {

    private FloatingActionButton addCourseFab, signOutFab;
    private final List<Course> courseList = new ArrayList<>();
    private final List<Course> performanceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize ViewPager and TabLayout
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        addCourseFab = findViewById(R.id.addCourseFab);
        signOutFab = findViewById(R.id.signOutFab);

        // Initialize course data
        courseList.add(new Course("Intro to Java", "Java basics for beginners."));
        courseList.add(new Course("Data Structures", "Learn arrays, lists, trees, and more."));

        // Initialize performance data
        performanceList.add(new Course("John Doe", "Completed: 12/15 assignments"));
        performanceList.add(new Course("Jane Smith", "Completed: 10/15 assignments"));
        performanceList.add(new Course("Alex Johnson", "Completed: 14/15 assignments"));

        // Sign out button
        signOutFab.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        addCourseFab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddCourseActivity.class);
            startActivityForResult(intent, 1001);
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CourseFragment.newInstance(courseList), "Courses");
        adapter.addFragment(PerformanceFragment.newInstance(performanceList), "Student Performance");
        adapter.addFragment(new EngagementFragment(), "Course Engagement");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("description");
            courseList.add(new Course(title, desc));

            // Update adapter through fragment
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + 0);
            if (fragment instanceof CourseFragment) {
                ((CourseFragment) fragment).updateCourseList();
            }
        }
    }

    // Fragment for Courses
    public static class CourseFragment extends Fragment {
        private CourseAdapter adapter;
        private List<Course> courseList;

        public static CourseFragment newInstance(List<Course> courseList) {
            CourseFragment fragment = new CourseFragment();
            fragment.courseList = courseList;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_courses, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.courseRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new CourseAdapter(courseList);
            recyclerView.setAdapter(adapter);
            return view;
        }

        public void updateCourseList() {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    // Fragment for Student Performance
    public static class PerformanceFragment extends Fragment {
        private List<Course> performanceList;

        public static PerformanceFragment newInstance(List<Course> performanceList) {
            PerformanceFragment fragment = new PerformanceFragment();
            fragment.performanceList = performanceList;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_performance, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.performanceRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            PerformanceAdapter adapter = new PerformanceAdapter(performanceList);
            recyclerView.setAdapter(adapter);
            return view;
        }
    }

    // Fragment for Course Engagement
    public static class EngagementFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_engagement, container, false);
        }
    }

    // ViewPager Adapter
    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}