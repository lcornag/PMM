package com.example.luca.transporte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PantallaEnvio extends Activity {
    Context context;
    Zona zona;
    SQLiteHelper cliBD;
    SQLiteDatabase basedatos;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_pantalla);

        final TextView zonaenvio = (TextView)findViewById(R.id.zonaenvio);
        final TextView continente = (TextView)findViewById(R.id.lblContinente);
        final TextView tarifa = (TextView)findViewById(R.id.tarifa);
        final TextView peso = (TextView)findViewById(R.id.peso);
        final TextView decoracion =  (TextView)findViewById(R.id.preferencia);
        final TextView costefinal = (TextView)findViewById(R.id.costefinal);
        final ImageView mapazona = (ImageView)findViewById(R.id.mapazona);
        final TextView textView = (TextView)findViewById(R.id.precio);
        final TextView textView2 = (TextView)findViewById(R.id.precioextra);

        Bundle bundle = getIntent().getExtras();

        String Zona = bundle.getString("Zona");
        String Continente = bundle.getString("Continente");
        String urgencia = bundle.getString("Urgencia");
        String Preferencia = bundle.getString("Preferencia");
        int Peso = bundle.getInt("Peso");
        int img = bundle.getInt("Imagen");
        double precio = bundle.getDouble("Precio");
        double precioExtra = bundle.getDouble("PrecioExtra");
        double precioFinal = bundle.getDouble("Precio Final");


        zonaenvio.setText("Zona :"+Zona);
        continente.setText("Continente: "+Continente);
        tarifa.setText("Tarifa: "+urgencia);
        peso.setText("Peso: "+Peso);
        decoracion.setText("Preferencia: "+Preferencia);

        textView.setText("precio por zona: "+ precio);
        textView2.setText("precio extra: "+ precioExtra);
        costefinal.setText("Precio final: "+precioFinal);
        mapazona.setImageResource(img);

        Button cargar = (Button)findViewById(R.id.guardar);
        zona = new Zona(Zona, Continente, img, ""+precioFinal);

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliBD = new SQLiteHelper(PantallaEnvio.this, "DBClientes", null, 1);
                basedatos = cliBD.getWritableDatabase();
                cliBD.insertarBD(basedatos, PantallaEnvio.this, zona);
            }
        });

        Button visualizar = (Button)findViewById(R.id.visualizar);
        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VerRegistros.class);
                startActivity(intent);
            }
        });
    }
}