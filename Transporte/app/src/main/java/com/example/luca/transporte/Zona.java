package com.example.luca.transporte;

public class Zona {
    private String zona;
    private String continente;
    private String precio;
    private int imagen;

    public Zona(String zo, String con, int imagen, String pr){
        this.zona = zo;
        this.continente = con;
        this.precio = pr;
        this.imagen = imagen;
    }

    public String getZona(){return zona;}

    public String getContinente(){return continente;}

    public String getPrecio(){return precio;}

    public int getImagen(){return imagen;}
}
