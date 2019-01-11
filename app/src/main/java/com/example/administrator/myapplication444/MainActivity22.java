package com.example.administrator.myapplication444;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity22 extends AppCompatActivity {
    private TextView mtvUser,mtvName;
    private Button people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String name = intent.getStringExtra("name");

        mtvUser=findViewById(R.id.user);
        mtvName=findViewById(R.id.name);
        people=findViewById(R.id.people);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity22.this,Duqu.class);
                startActivity(intent);
            }
        });


        Utils.postLogin(user, name, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data=response.body().string();
                showData(data);

            }
        });
    }
    private void showData(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(msg);
                    String status = jsonObject.getString("status");
                    final String user = jsonObject.getString("user");
                    final String name = jsonObject.getString("name");
                    Toast.makeText(MainActivity22.this,status,Toast.LENGTH_SHORT).show();
                    mtvUser.setText(user);
                    mtvName.setText(name);
                    Button btn=findViewById(R.id.talkroom);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            Intent intent = new Intent(MainActivity22.this,Talk.class);
                            intent.putExtra("user",user);
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

    }
}
