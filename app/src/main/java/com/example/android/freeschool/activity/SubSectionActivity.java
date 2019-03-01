package com.example.android.freeschool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.freeschool.R;
import com.example.android.freeschool.adapter.SubSectionAdapter;
import com.example.android.freeschool.api.ApiInterface;
import com.example.android.freeschool.api.ApiUtils;
import com.example.android.freeschool.pojo.SubSection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubSectionActivity extends AppCompatActivity {

    public static final String TAG = SubSectionActivity.class.getSimpleName();

    private ApiInterface apiInterface = ApiUtils.getSOService();

    private ListView sectionListView;

    private List<SubSection> sectionList;

    private String getKey;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_section);
        getKey = getIntent().getStringExtra("key");

        getSupportActionBar().setTitle(getKey); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        // Find a reference to the {@link ListView} in the layout
        sectionListView = findViewById(R.id.list);

        progressBar = findViewById(R.id.loading);

        getSubSection(getKey);

        // Set an item click listener for ListView
        sectionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Attach code to perform when item is clicked*/
            }
        });

    }

    private void getSubSection(String id) {
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getSubSections(id).enqueue(new Callback<List<SubSection>>() {
            @Override
            public void onResponse(Call<List<SubSection>> call, Response<List<SubSection>> response) {
                Log.i(TAG, "onResponse: " + response.raw());
                if (response.isSuccessful()) {
                    sectionList = response.body();
                    sectionListView.setAdapter(new SubSectionAdapter(SubSectionActivity.this, sectionList));
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<SubSection>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(SubSectionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
