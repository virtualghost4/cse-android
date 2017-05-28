package com.example.zhelon.camaraaplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    public static final String PREFS_NAME = "MyCamaraPref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(checkIfJoined()){
            startCamaraActivity();

        }else{
            email = (EditText) findViewById(R.id.email);
            password = (EditText) findViewById(R.id.password);
            login = (Button) findViewById(R.id.submit);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(email.getText().toString().equals("") || password.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Ingrese campos", Toast.LENGTH_LONG).show();
                    }else{

                        createCheckPoint();
                        startCamaraActivity();

                    }
                }
            });
        }


    }

    private void startCamaraActivity() {
        Intent intent = new Intent(getBaseContext(), TabActivity.class);
        Toast.makeText(getApplicationContext(),"Ingresando...", Toast.LENGTH_LONG).show();
        finish();
        startActivity(intent);
    }

    private boolean checkIfJoined() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("joined", false);
        return silent;

    }

    private void createCheckPoint() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("joined",true);
        editor.putString("email", email.getText().toString());
        editor.commit();

    }
}
