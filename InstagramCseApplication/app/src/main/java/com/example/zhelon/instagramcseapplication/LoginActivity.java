package com.example.zhelon.instagramcseapplication;

import android.content.Intent;
import android.content.SharedPreferences;
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

        if(isLogged()){

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();

        }else{

        b1 = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putString("Name", username.getText().toString());

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtras(bundle);


                createCheckPoint();
                Toast.makeText(getApplicationContext(),"Bienvenido", Toast.LENGTH_SHORT).show();

                startActivity(i);


            }
        });
        }

    }

    private boolean isLogged() {
        SharedPreferences settings = getSharedPreferences("preference", 0);
        return settings.getBoolean("check", false);
    }

    private void createCheckPoint() {

        SharedPreferences settings = getSharedPreferences("preference", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("check", true);
        editor.putString("name", username.getText().toString());
        editor.commit();
    }

}