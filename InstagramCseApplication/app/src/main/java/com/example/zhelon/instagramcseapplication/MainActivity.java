package com.example.zhelon.instagramcseapplication;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.zhelon.instagramcseapplication.adapter.PhotoAdapter;
import com.example.zhelon.instagramcseapplication.pojo.Photo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyler = (RecyclerView) findViewById(R.id.recyclerview);
        manager = new LinearLayoutManager(this);
        recyler.setLayoutManager(manager);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Photo p = new Photo(byteArray, "Hola ");
        List<Photo> list = new ArrayList<Photo>();
        list.add(p);

        adapter = new PhotoAdapter(list);
        recyler.setAdapter(adapter);


        //recyler.setHasFixedSize(true);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 110);
        } else {
            Toast.makeText(getApplicationContext(),"Permisos de camara concedidos", Toast.LENGTH_SHORT).show();

        }

    }




}
