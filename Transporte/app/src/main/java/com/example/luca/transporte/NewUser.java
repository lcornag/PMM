package com.example.luca.transporte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewUser extends AppCompatActivity{
    Usuarios usuarios;

    Button guardar;
    EditText entryUsuario;
    EditText entryContraseña;

    String usuario;
    String contraseña;

    SQLiteHelper cliBD;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        entryUsuario =(EditText)findViewById(R.id.entryUsuario);
        entryContraseña = (EditText)findViewById(R.id.entryContraseña);
        guardar = (Button)findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = entryUsuario.getText().toString();
                contraseña = entryContraseña.getText().toString();
                if( usuario.length()>0 && contraseña.length()>0){
                    usuarios = new Usuarios(usuario, contraseña);
                    cliBD=new SQLiteHelper(NewUser.this, "DBClientes", null, 1);
                    db=cliBD.getWritableDatabase();
                    cliBD.insertarBDUsuario(db,NewUser.this,usuarios);

                    Toast.makeText(NewUser.this, "Usuario registrado!", Toast.LENGTH_SHORT).show();
                    cliBD.close();
                    db.close();
                }
                else{
                    Toast.makeText(NewUser.this, "Asegurate de rellenar ambos campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
