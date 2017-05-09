package com.example.zhelon.camaraaplication;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhelon.camaraaplication.adapter.PhotoAdapter;
import com.example.zhelon.camaraapplication.pojo.Photo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    TextView saludos;
    ListView mList;
    Button camara;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private static final int CAMERA_PIC_REQUEST = 200;
    public static final String PREFS_NAME = "MyCamaraPref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saludos = (TextView) findViewById(R.id.saludos);
        camara = (Button) findViewById(R.id.open_camara);

        saludos.setText("Hola " + getEmail());

        createDummyReciclerView();

        creatFolder();

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
                } else {
                    Intent it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(it, CAMERA_PIC_REQUEST);                }

            }
        });



    }

    private void createDummyReciclerView() {

        List items = new ArrayList();

        items.add(new Photo(R.drawable.foto1, "Mujer", 230));
        items.add(new Photo(R.drawable.foto2, "Peruano Nazi", 3425));
        items.add(new Photo(R.drawable.foto3, "Virgen", 456));
        

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

    // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

    // Crear un nuevo adaptador
        adapter = new PhotoAdapter(items);
        recycler.setAdapter(adapter);
    }


    private String getEmail() {

        SharedPreferences setting = getSharedPreferences(PREFS_NAME, 0);
        return setting.getString("email","hola@hotmail.com.com");
    }

    private void creatFolder() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/camaraapp/");
        if(!file.exists()){
            file.mkdirs();

        }
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
            //Se crea un archivo vacio y se le da el nombre
           // File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/camaraapp/"+"image.jpg");

            try {
                file.createNewFile();
                FileOutputStream fo = new FileOutputStream(file);
                //Le pasamos la foto en bytearray al archivo recien creado
                fo.write(bytes.toByteArray());
                fo.close();
                Toast.makeText(getApplicationContext(), "Guardando foto...", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Log.e("MUrio", e.getMessage());
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
