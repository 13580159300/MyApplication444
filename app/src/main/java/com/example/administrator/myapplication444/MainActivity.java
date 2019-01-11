package com.example.administrator.myapplication444;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private EditText r_user,r_password;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        r_user=findViewById(R.id.r_user);

        r_password=findViewById(R.id.r_password);

        btn=findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity22.class);
                intent.putExtra("user", r_user.getText().toString().trim());
                intent.putExtra("name", r_password.getText().toString().trim());
                startActivity(intent);
            }
        });

    }

}
