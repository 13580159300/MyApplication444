package com.example.administrator.myapplication444;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Duqu extends AppCompatActivity {
    private ListView mListView;
    private Utils HttpUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duqu);
        mListView = findViewById(R.id.lv);

        HttpUtils.httpUtils(new Callback() {
            public void onFailure(Call call, IOException e) {
            }

            public void onResponse(Call call, final Response response) throws IOException {
                String data = response.body().string();
                showData(data);
            }
        });
    }

    private void showData(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();

                try {
                    JSONArray jsonArray=new JSONArray(data);
                    List<UserBean> list=new ArrayList<UserBean>();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        UserBean userBean=new UserBean();
                        userBean.setName(jsonObject.getString("name"));
                        userBean.setUser(jsonObject.getString("user"));
                        list.add(userBean);
                    }
                    SimpleAdapter simpleAdapter=new SimpleAdapter(getApplicationContext(),getData(list),R.layout.list_xinxi,new String[]{"name","user"},new int[]{R.id.name,R.id.user});
                    mListView.setAdapter(simpleAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    List<Map<String,String>> getData(List<UserBean> list_){
        Map<String,String> map;
        List<Map<String,String>> list=new ArrayList<Map<String, String>>();
        for (UserBean userBean:list_){
            map=new HashMap<String,String>();
            map.put("name",userBean.getName());
            map.put("user",userBean.getUser());
            list.add(map);
        }
        return list;
    }
}



