package com.example.administrator.myapplication444;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Utils {
    private static final String URL = "http://123.207.85.214/chat/member.php";
    private static final String LoginURL = "http://123.207.85.214/chat/login.php";
    private static final String Send = "http://123.207.85.214/chat/chat1.php";

    public static void GetContacts(Callback callback) {
        OkHttpClient client = new OkHttpClient();//第一步：okhttp对象创建
        Request request = new Request.Builder()
                .url(URL)                       //第二步：Requset对象创建
                .build();
        client.newCall(request).enqueue(callback);  // client.newCall(request)call对象
    }

    public static void postLogin(String user, String password, Callback callback){
        OkHttpClient client = new OkHttpClient();//第一步：okhttp对象创建
        RequestBody body=new FormBody.Builder()     //第二部：保存登录信息。
                .add("user",user)
                .add("password",password)
                .build();
        Request request = new Request.Builder()     //第三步：创建request对象，添加post请求方式
                .url(LoginURL)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);      // client.newCall(request)call对象

    }

    public static void postSend(String user, String chat, Callback callback){
        OkHttpClient client = new OkHttpClient();//第一步：okhttp对象创建
        RequestBody body=new FormBody.Builder()     //第二部：保存登录信息。
                .add("user",user)
                .add("chat",chat)
                .build();
        Request request = new Request.Builder()     //第三步：创建request对象，添加post请求方式
                .url(Send)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);      // client.newCall(request)call对象

    }

    public static void httpUtils(Callback callback){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            client.newCall(request).enqueue(callback);

        }
}
