package com.example.administrator.myapplication444;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

public class Talk extends AppCompatActivity {
    private EditText xinxi;
    private TextView jilu;
    private Button send;
    //private String chat;
    private ListView mListView;
//    private TextView user1,content1,time1;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            SimpleAdapter simpleAdapter=new SimpleAdapter(getApplicationContext(),getData((List<ChatBean>) msg.obj),R.layout.list_talk_my,new String[]{"name","chat","time"},new int[]{R.id.name,R.id.content,R.id.time});
            mListView.setAdapter(simpleAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        Intent intent = getIntent();
        final String user = intent.getStringExtra("user");

        send = findViewById(R.id.send);
        mListView=findViewById(R.id.list);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xinxi = findViewById(R.id.xinxi);
                String chat = xinxi.getText().toString().trim();
                Utils.postSend(user,chat,new Callback() {
                    @Override
                    public void onFailure(Call call,IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String data=response.body().string();
                        Log.d("ssa",data);
                        showData(data);

                    }
                });
            }
        });


    }
    List<Map<String,String>> getData(List<ChatBean> list){
        Map<String,String> map;
        List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
        for (ChatBean chatBean:list){
            map=new HashMap<String,String>();
            map.put("name",chatBean.getName());
            map.put("chat",chatBean.getChat());
            map.put("time",chatBean.getTime());
            list1.add(map);
        }
        return list1;
    }




    private void showData(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ChatBean> list=new ArrayList<ChatBean>();
                try {
                    JSONArray jsonArray= new JSONArray(msg);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        ChatBean chatBean=new ChatBean();
                        String name = jsonObject.optString("name");
                        String chat = jsonObject.optString("chat");
                        String time = jsonObject.optString("time");
                        chatBean.setName(name);
                        chatBean.setChat(chat);
                        chatBean.setTime(time);
                        list.add(chatBean);
                    }
                    Message message=new Message();
                    message.obj=list;
                    mHandler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
