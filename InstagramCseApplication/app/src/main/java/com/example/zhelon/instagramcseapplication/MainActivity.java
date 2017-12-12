package com.example.zhelon.instagramcseapplication;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.zhelon.instagramcseapplication.adapter.PhotoAdapter;
import com.example.zhelon.instagramcseapplication.pojo.Photo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private FloatingActionButton floatingActionButton;
    private static final int CAMERA_PIC_REQUEST = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //floatingButton
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        recyler = (RecyclerView) findViewById(R.id.recyclerview);
        manager = new LinearLayoutManager(this);
        recyler.setLayoutManager(manager);


        List<Photo> listPhoto = getAllPhoto();

        adapter = new PhotoAdapter(listPhoto);
        recyler.setAdapter(adapter);
        recyler.setHasFixedSize(true);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 110);
        } else {
            Toast.makeText(getApplicationContext(),"Permisos de camara concedidos", Toast.LENGTH_SHORT).show();

        }



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(MainActivity.this
                            , new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 110);

                    creatFolder();

                } else {

                    //Intent camara
                    Intent it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(it, CAMERA_PIC_REQUEST);
                }
            }
        });

    }

    private List<Photo> getAllPhoto() {
        Realm realms = Realm.getDefaultInstance();
        RealmResults<Photo> result = realms.where(Photo.class).findAll();

        return result;


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,  data);

        if (requestCode == CAMERA_PIC_REQUEST) {

            //Obtenemos la foto, viene dentro de un bundle como "data"
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //La imagen se comprime, usando formato JPEG, calidad y el outputstream
            thumbnail.compress(Bitmap.CompressFormat.JPEG,  100, bytes);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/camaraapp/"+"image.jpg");
            Log.i("MUrio", file.getAbsolutePath());

            try {
                file.createNewFile();
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(bytes.toByteArray());
                fo.close();
                Toast.makeText(getApplicationContext(), "Guardando foto...", Toast.LENGTH_SHORT).show();

                persistPhoto(bytes.toByteArray());

            } catch (IOException e) {
                Log.e("MUrio", e.getMessage());
                Toast.makeText(getApplicationContext(), "Murio...", Toast.LENGTH_SHORT).show();

                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void persistPhoto(byte[] image) {
        Realm realms = Realm.getDefaultInstance();
        realms.beginTransaction();
        Photo photo = realms.createObject(Photo.class);
        photo.setComment("Hola Mundo");
        photo.setId(UUID.randomUUID().toString());
        photo.setImage(image);




    }

    private void creatFolder() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/camaraapp/");
        if(!file.exists()){
            file.mkdirs();

        }
    }



}
