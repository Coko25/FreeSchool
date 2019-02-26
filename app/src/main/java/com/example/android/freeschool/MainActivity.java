package com.example.android.freeschool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the list of courses from (@link QueryUtils)
        ArrayList<Course> courses = QueryUtils.extractCourse();


        // Find a reference to the {@link ListView} in the layout
        ListView courseListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes the list of courses as input
        CourseAdapter adapter = new CourseAdapter(this, courses);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        courseListView.setAdapter(adapter);

        // Set an item click listener for ListView
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the list of sections from(@link QueryUtils)
                ArrayList<Section> sections = QueryUtils.extractSection();

                //Find a reference to the {@link ListView} in the layout
                ListView courseListView = (ListView) findViewById(R.id.list);

                //Create a new adapter that takes the list of sections as input
                SectionAdapter adapter = new SectionAdapter(this, sections);

                // Set the adapter on the {@link ListView}
                // so the list can be populated in the user interface
                courseListView.setAdapter(adapter);

            }
        });
    }
}
