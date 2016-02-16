package com.example.luca.transporte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    Context context;
    Usuarios usuarios;

    EditText usuario;
    EditText contraseña;
    String user;
    String pwd;

    SQLiteHelper cliBD;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        usuario = (EditText)findViewById(R.id.entryUsuario);
        contraseña = (EditText)findViewById(R.id.entryContraseña);

        Button iniciar = (Button)findViewById(R.id.iniciar);
        Button registrar = (Button)findViewById(R.id.sincuenta);

        iniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user = usuario.getText().toString();
                pwd = contraseña.getText().toString();

                if( user!=null && pwd!=null){
                    Usuarios nuevo = new Usuarios(user, pwd);

                    cliBD = new SQLiteHelper(Login.this, "DBClientes", null, 1);
                    db = cliBD.getReadableDatabase();
                    Usuarios[] users = cliBD.listarU(db);

                    for (int i=0; i<users.length; i++){
                        if(nuevo.getNombre().equals(users[i].getNombre()) && nuevo.getPwd().equals(users[i].getPwd())){
                            Toast.makeText(Login.this, "Sesion iniciada como usuario " + nuevo.getNombre() + " Volviendo a la pantalla 1", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            String userActivo=users[i].getNombre();

                            Bundle bundle = new Bundle();
                            bundle.putString("Nombre", userActivo);

                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    cliBD.close();
                    db.close();
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NewUser.class);
                startActivity(intent);
            }
        });
    }
}
