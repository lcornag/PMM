package com.example.mati.spinnercompleto;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Persona[] personas =

            new Persona[]{
                    new Persona("menu 1","Sweet and sour fish fillet"),
                    new Persona("menu 2","Pawn tempura"),
                    new Persona("menu 3","Crab with sotanghon"),
                    new Persona("menu 4","Warm lobster salad"),
                    new Persona("menu 5","Yang chow fried rice")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);

        Spinner miSpinner = (Spinner)findViewById(R.id.SpinnerSimple);

        miSpinner.setAdapter(adaptador);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {

                ImageView img = (ImageView) findViewById(R.id.imageView);
                if (personas[position].equals(personas[0])) {
                    img.setImageResource(R.drawable.fish);
                } else if (personas[position].equals(personas[1])) {
                    img.setImageResource(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final RadioGroup rg = (RadioGroup) findViewById(R.id.radiogrp);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            }
        });

        final Button aceptar =(Button)findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), Activity2.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }



    class AdaptadorTitulares extends ArrayAdapter {

        Activity context;

        AdaptadorTitulares(Activity context) {
            super(context, R.layout.itemspersona, personas);
            this.context = context;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vistaDesplegada = getView(position,convertView,parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.itemspersona, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.Lblnombre);
            lblTitulo.setText(personas[position].getNombre());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.Lbledad);
            lblSubtitulo.setText(personas[position].getEdad());

            return(item);
        }
    }


}
