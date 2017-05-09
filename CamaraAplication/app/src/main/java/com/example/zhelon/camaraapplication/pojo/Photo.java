package com.example.zhelon.camaraapplication.pojo;

/**
 * Created by zhelon on 02-05-17.
 */

public class Photo {
        private int imagen;
        private String nombre;
        private int visitas;

        public Photo(int imagen, String nombre, int visitas) {
            this.imagen = imagen;
            this.nombre = nombre;
            this.visitas = visitas;
        }

        public String getNombre() {
            return nombre;
        }

        public int getVisitas() {
            return visitas;
        }

        public int getImagen() {
            return imagen;
        }

}
