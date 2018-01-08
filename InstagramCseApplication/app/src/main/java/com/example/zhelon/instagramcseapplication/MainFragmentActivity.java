package com.example.zhelon.instagramcseapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.zhelon.instagramcseapplication.fragment.PhotoListFragment;
import com.example.zhelon.instagramcseapplication.pojo.Photo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import io.realm.Realm;

public class MainFragmentActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;


    private FloatingActionButton floatingActionButton;
    private static final int CAMERA_PIC_REQUEST = 200;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);



        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(MainFragmentActivity.this
                            , new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 110);

                } else {

                    //Intent camara
                    Intent it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(it, CAMERA_PIC_REQUEST);
                }
            }
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position){
                case 0:
                    return PhotoListFragment.newInstance(position);

                default:
                    return null;

            }

        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.i("Position", ""+position);

            switch (position) {
                case 0:
                    return "Photo List";
                case 1:
                    return "Setting";

            }
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,  data);

        if(data != null) {

            if (requestCode == CAMERA_PIC_REQUEST) {

                //Obtenemos la foto, viene dentro de un bundle como "data"
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                //La imagen se comprime, usando formato JPEG, calidad y el outputstream
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/camaraapp/" + "image.jpg");
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
    }

    private void persistPhoto(byte[] image) {
        Realm realms = Realm.getDefaultInstance();

        realms.beginTransaction();
        Photo photo = realms.createObject(Photo.class);
        photo.setComment("Hola Mundo");
        photo.setId(UUID.randomUUID().toString());
        photo.setImage(image);
        realms.commitTransaction();

    }

}
