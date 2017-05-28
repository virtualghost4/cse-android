package com.example.zhelon.camaraapplication.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by zhelon on 02-05-17.
 */

public class Photo extends RealmObject {

        @PrimaryKey
        private String id;
        private byte[] foto;
        @Required
        private String nombre;
        private int visitas;

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNombre() {
            return nombre;
        }

        public int getVisitas() {
            return visitas;
        }


    public void setVisitas(int visitas){
        this.visitas = visitas;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
