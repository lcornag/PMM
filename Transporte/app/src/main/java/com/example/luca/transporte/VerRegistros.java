package com.example.luca.transporte;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class VerRegistros extends Activity {
        Zona[] zonas ;
        String zon, cont;
        double pr;
        int img;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ver_registros);

            zonas = listarBD(this);

            ListView lista =(ListView) findViewById(R.id.listview);
            ListAdapter adaptador = new ListAdapter(this);
            lista.setAdapter(adaptador);

        }
        public Zona[] listarBD(Context context){
            SQLiteHelper cliBDh = new SQLiteHelper(context, "DBClientes", null, 1);
            SQLiteDatabase db = cliBDh.getReadableDatabase();
            return cliBDh.listar(db);
        }

        public class ListAdapter extends ArrayAdapter {
            Activity main;
            public ListAdapter (Activity activity){
                super(activity, R.layout.itemszonas, zonas);
                main=activity;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.itemszonas, null);

                TextView zona = (TextView) convertView.findViewById(R.id.lblZona);
                zona.setText(zonas[position].getZona());

                TextView continente = (TextView) convertView.findViewById(R.id.lblContinente);
                continente.setText(zonas[position].getContinente());

                TextView precio = (TextView) convertView.findViewById(R.id.lblPrecio);
                precio.setText(String.valueOf(zonas[position].getPrecio()));

                ImageView imagen= (ImageView) convertView.findViewById(R.id.Imagen);
                imagen.setImageResource(zonas[position].getImagen());



                zon = zonas[position].getZona();
                cont = zonas[position].getContinente();
                pr = Double.parseDouble(zonas[position].getPrecio());
                img = zonas[position].getImagen();

                return convertView;
            }
        }
    }
