package com.example.zhelon.camaraapplication.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by zhelon on 02-05-17.
 */

public class Photo extends RealmObject {

        @PrimaryKey
        private int id;
        private int imagen;
        @Required
        private String nombre;
        private int visitas;

        public String getNombre() {
            return nombre;
        }

        public int getVisitas() {
            return visitas;
        }

        public int getImagen() {
            return imagen;
        }

    public void setVisitas(int visitas){
        this.visitas = visitas;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
