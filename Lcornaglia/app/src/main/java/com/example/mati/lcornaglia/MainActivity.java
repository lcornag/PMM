package com.example.mati.lcornaglia;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    double envio;
    double extra;
    double uni;

    private Pizzas[] Pizza =

            new Pizzas[]{
                    new Pizzas("MARGARITA", "jamon/tomate","12eur"),
                    new Pizzas("TRES QUESOS","roquefort,emental,manchego","15eur"),
                    new Pizzas("BARBACOA","carne/tomate","18eur")
            };

    EditText entry;
    TextView precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        Spinner miSpinner = (Spinner)findViewById(R.id.SpinnerPizzas);

        miSpinner.setAdapter(adaptador);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final RadioGroup rgp = (RadioGroup)findViewById(R.id.radiogrp);
        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getCheckedRadioButtonId() == R.id.domicilio) {
                    envio = 1.1;
                }
                else{
                    envio =1;
                }
            }
        });

        CheckBox grande = (CheckBox)findViewById(R.id.chkBoxGrande);
        CheckBox ingredientes =(CheckBox)findViewById(R.id.chkBoxIngredientes);
        CheckBox queso =(CheckBox)findViewById(R.id.chkBoxQueso);
        if(grande.isChecked()){
            extra= extra +1;
        }
        if(ingredientes.isChecked()){
            extra= extra+1;
        }
        if(queso.isChecked()){
            extra=extra+1;
        }

        entry = (EditText)findViewById(R.id.entry);
        precio =(TextView)findViewById(R.id.preciofinal);

        Button total = (Button)findViewById(R.id.totalpedido);
        total.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                uni = Integer.parseInt(entry.getText().toString());

                if(Pizza[position]= Pizzas[0]){
                    precio.setText(((12+extra)*uni)*envio).toString();
                }
                else if(Pizza[position]= Pizzas[1]){
                    precio.setText(((15+extra)*uni)*envio).toString();
                }
                else if(Pizza[position]= Pizzas[2]){
                    precio.setText(((18+extra)*uni)*envio).toString();
                }
            }
        });

        final Button factura =(Button)findViewById(R.id.factura);
        factura.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity2.class);
                startActivity(myIntent);
            }
        });
    }


    class AdaptadorTitulares extends ArrayAdapter {

        Activity context;

        AdaptadorTitulares(Activity context) {
            super(context, R.layout.itemspizzas,Pizza);
            this.context = context;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vistaDesplegada = getView(position,convertView,parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.itemspizzas, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.Lblnombre);
            lblTitulo.setText(Pizza[position].getNombre());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.Lbldescripcion);
            lblSubtitulo.setText(Pizza[position].getDescripcion());

            TextView lblSubtitulo2 = (TextView)item.findViewById(R.id.Lblprecio);
            lblSubtitulo2.setText(Pizza[position].getPrecio());

            ImageView lblimagen = (ImageView)item.findViewById(R.id.Lblimagen);

            if(Pizza[position].equals(Pizza[0])) {
                lblimagen.setBackgroundResource(R.drawable.pizza1);
            }
            else if(Pizza[position].equals(Pizza[1])){
                lblimagen.setBackgroundResource(R.drawable.pizza2);
            }
            else if(Pizza[position].equals(Pizza[2])){
                lblimagen.setBackgroundResource(R.drawable.pizza3);
            }
            return(item);
        }
    }
}
