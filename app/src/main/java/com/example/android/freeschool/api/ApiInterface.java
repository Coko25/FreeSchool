package com.example.android.freeschool.api;

import com.example.android.freeschool.pojo.Course;
import com.example.android.freeschool.pojo.Section;
import com.example.android.freeschool.pojo.SubSection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("curriculum/courses")
    Call<List<Course>> getUserList();

    @GET("curriculum/sections/in/course/{course-id}")
    Call<List<Section>> getSections(@Path("course-id") String id);

    @GET("curriculum/subsections/in/section/{section-id}")
    Call<List<SubSection>> getSubSections(@Path("section-id") String id);
}
