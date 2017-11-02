package com.example.zhelon.instagramcseapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button b1;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Bienvenido", Toast.LENGTH_SHORT).show();

            }
        });


    }

}