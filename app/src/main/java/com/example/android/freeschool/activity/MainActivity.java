package com.example.android.freeschool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.freeschool.R;
import com.example.android.freeschool.adapter.CourseAdapter;
import com.example.android.freeschool.api.ApiInterface;
import com.example.android.freeschool.api.ApiUtils;
import com.example.android.freeschool.pojo.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private ApiInterface apiInterface = ApiUtils.getSOService();

    private ListView courseListView;

    private List<Course> courseList;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        courseListView = findViewById(R.id.list);

        progressBar = findViewById(R.id.loading);

        getCoursesList();

        // Set an item click listener for ListView
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = courseList.get(position);
                Intent intent = new Intent(MainActivity.this, SectionActivity.class);
                intent.putExtra("key", course.getId());
                startActivity(intent);
            }
        });

    }

    private void getCoursesList() {
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getUserList().enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                Log.i(TAG, "onResponse: " + response.raw());
                if (response.isSuccessful()) {
                    courseList = response.body();
                    courseListView.setAdapter(new CourseAdapter(MainActivity.this, courseList));
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
