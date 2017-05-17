package com.example.zhelon.camaraaplication.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhelon.camaraaplication.MainActivity;
import com.example.zhelon.camaraaplication.R;
import com.example.zhelon.camaraaplication.TabActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment {


    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        EditText mUserName = (EditText) view.findViewById(R.id.user_name);
        Button mOpenCamara = (Button) view.findViewById(R.id.open_camara);
        mOpenCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
                } else {
                    Intent it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(it, 200);                }

            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,  data);

        if (requestCode == 200) {

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
                Toast.makeText(getActivity().getApplicationContext(), "Guardando foto...", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Log.e("MUrio", e.getMessage());
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
