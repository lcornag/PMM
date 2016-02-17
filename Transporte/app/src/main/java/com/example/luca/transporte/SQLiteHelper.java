package com.example.luca.transporte;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SQLiteHelper extends SQLiteOpenHelper {
    String cadSQL = "CREATE TABLE Destinos (id INTEGER PRIMARY KEY, zona TEXT,continente TEXT, imagen INTEGER, precio TEXT )";
    String cadSQLUsuarios = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY, nombre TEXT, password TEXT)";
    String cadListar = "SELECT * FROM Destinos";
    String cadListarU = "SELECT * FROM Usuarios";

    public SQLiteHelper(Context contexto, String nombre, CursorFactory almacen, int version){
        super(contexto, nombre, almacen, version);
    }

    public void insertarBD( SQLiteDatabase db, Context context, Zona Zona ){
        if(db!=null){
            db.execSQL("INSERT INTO Destinos (zona, continente, imagen, precio) " +
                    "VALUES ('" + Zona.getZona() + "', '" + Zona.getContinente() + "', '" + Zona.getImagen() + "', '" + Zona.getPrecio() + "')");
            db.close();
        }
        else{
            Toast.makeText(context, "Error al conectarse", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertarBDUsuario ( SQLiteDatabase db, Context context, Usuarios usuario){
        if(db!=null){
            db.execSQL("INSERT INTO Usuarios (nombre, password)" +
                    "VALUES ('" +usuario.getNombre() + "', '"+usuario.getPwd()+"')");
            db.close();
        }
        else{
            Toast.makeText(context, "Error al conectarse", Toast.LENGTH_SHORT).show();
        }
    }

    public Zona[] listar(SQLiteDatabase db){
        Zona auxzona;
        int cont = 0;

        Cursor cursor = db.rawQuery(cadListar, null);
        cursor.moveToFirst();
        Zona[] zonas = new Zona[cursor.getCount()];

        do {
            auxzona=new Zona(cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4));
            zonas[cont]=auxzona;
            cont++;
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return zonas;
    }

    public Usuarios[] listarU(SQLiteDatabase db){
        Usuarios user;
        Cursor cursor = db.rawQuery(cadListarU, null);
        cursor.moveToFirst();
        Usuarios[] users = new Usuarios[cursor.getCount()];
        int cont = 0;
        do{
            user = new Usuarios(cursor.getString(1),cursor.getString(2));
            users[cont] = user;
            cont++;
        }while(cursor.moveToNext());

        cursor.close();
        db.close();
        return users;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(cadSQL);
        db.execSQL(cadSQLUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS Destinos");
        bd.execSQL(cadSQL);
    }
}
