package com.example.mati.spinnercompleto;

public class Persona {
    private String nombre;
    private String edad;

    public Persona(String nom, String ed){
        nombre = nom;
        edad = ed;
    }

    public String getNombre(){return nombre;}

    public String getEdad(){
        String newEdad = edad + "";
        return newEdad;
    }

    public String toString(){
        String todo = nombre + " " + edad;
        return todo;
    }
}
