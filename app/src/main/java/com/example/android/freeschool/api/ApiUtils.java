package com.example.android.freeschool.api;

import com.example.android.freeschool.util.Config;

public class ApiUtils {


    private ApiUtils() {
    }

    public static ApiInterface getSOService() {
        return RetrofitClient.getClient(Config.BASE_URL).create(ApiInterface.class);
    }
}