package com.example.mati.mylistatitulares;

public class Titular {
    private String titulo;
    private String subtitulo;

    public Titular(String tit,String sub){
        titulo=tit;
        subtitulo=sub;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getSubtitulo(){
        return subtitulo;
    }
    public String toString(){
        String todo = titulo + " " + subtitulo;
        return todo;
    }
}
