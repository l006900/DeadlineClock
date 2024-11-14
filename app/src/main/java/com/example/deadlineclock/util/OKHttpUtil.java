package com.example.deadlineclock.util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpUtil {
    private OKHttpUtil(){}
    private OKHttpUtil instance = new OKHttpUtil();
    private OkHttpClient okHttpClient = new OkHttpClient();
    public OKHttpUtil getInstance(){
        return instance;
    }
    public String doGet(String url) throws Exception{
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().toString();
    }
}
