package com.example.mati.listasimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MyListaSimple extends AppCompatActivity {

    //ListView lview;
    Spinner miSpinner;
    final static String semana[]= { "Lunes" , "Martes" , "Miercoles" , "Jueves" , "Viernes" , "Sabado" , "Domingo" } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my_lista_simple);

        String mensaje;
        miSpinner = (Spinner)findViewById(R.id.Spinner1);
        //lview = (ListView)findViewById(R.id.myLista);

        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,semana);

        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(miAdaptador);
        /*miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                        String mensaje = "";
                        mensaje = "item clicked: " + semana[position];
                        showToast(mensaje);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                    });*/

    }

       /*lview.setAdapter(miAdaptador);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "";
                mensaje = "Item clicked =>" + semana[position];
                showToast(mensaje);
            }
        });

    }*/

    public void showToast(String text){
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_lista_simple, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
