package com.example.luca.transporte;

public class Usuarios {
    private String nombre;
    private String pwd;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Usuarios(String user, String pwd){
        this.nombre = user;
        this.pwd = pwd;
    }

}
