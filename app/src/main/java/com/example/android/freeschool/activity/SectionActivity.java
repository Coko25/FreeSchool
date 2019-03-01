package com.example.android.freeschool.activity;

import android.content.Intent;
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
import com.example.android.freeschool.adapter.SectionAdapter;
import com.example.android.freeschool.api.ApiInterface;
import com.example.android.freeschool.api.ApiUtils;
import com.example.android.freeschool.pojo.Section;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SectionActivity extends AppCompatActivity {

    public static final String TAG = SectionActivity.class.getSimpleName();

    private ApiInterface apiInterface = ApiUtils.getSOService();

    private ListView sectionListView;

    private List<Section> sectionList;

    private String getKey;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        getKey = getIntent().getStringExtra("key");

        getSupportActionBar().setTitle(getKey); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        // Find a reference to the {@link ListView} in the layout
        sectionListView = findViewById(R.id.list);

        progressBar = findViewById(R.id.loading);

        getSection(getKey);

        // Set an item click listener for ListView
        sectionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Section section = sectionList.get(position);
                Intent intent = new Intent(SectionActivity.this, SubSectionActivity.class);
                intent.putExtra("key", section.getId());
                startActivity(intent);
            }
        });

    }

    private void getSection(String id) {
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getSections(id).enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                Log.i(TAG, "onResponse: " + response.raw());
                if (response.isSuccessful()) {
                    sectionList = response.body();
                    sectionListView.setAdapter(new SectionAdapter(SectionActivity.this, sectionList));
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Section>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(SectionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
