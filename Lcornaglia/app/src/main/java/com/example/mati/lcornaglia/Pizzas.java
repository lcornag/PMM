package com.example.mati.lcornaglia;

import android.media.Image;

public class Pizzas {
    private String nombre;
    private String descripcion;
    private String precio;

    public Pizzas(String nom, String desc, String pre){
        nombre=nom;
        descripcion=desc;
        precio=pre;
    }
    public String getNombre(){return nombre;}
    public String getDescripcion(){return descripcion;}
    public String getPrecio(){return precio;}

}
