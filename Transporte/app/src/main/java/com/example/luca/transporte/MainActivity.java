package com.example.luca.transporte;

import android.app.Activity;
import android.app.AlertDialog;
import  android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Zona[] zonas =
            new Zona[]{
                    new Zona("Zona A","Asia/Oceanía",R.drawable.asiaoceania,"30"),
                    new Zona("Zona B","América/África",R.drawable.americaafrica,"20"),
                    new Zona("Zona C","Europa",R.drawable.europa,"10"),
            };

    private RadioGroup tarifasRadio;
    private RadioButton tarifanormal;
    private Button hacercalculos;
    private Spinner Zonas;
    private CheckBox cajaregalo, tarjetadedicada;
    private EditText peso;
    private TextView usuarioIniciado;
    int img;
    String zon;
    String cont;
    double pr;
    double precioExtra;
    double precioFinal;
    String urgencia = "Tarifa normal";
    String preferencia = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioIniciado=(TextView)findViewById(R.id.usuarioIniciado);
        Bundle bundle;
        if((bundle = getIntent().getExtras()) == null){
            new AlertDialog.Builder(this)
                .setMessage("No has iniciado sesión")
                .setPositiveButton("Iniciar sesión", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int x){
                        Intent intentent = new Intent(MainActivity.this, Login.class);
                        startActivity(intentent);
                    }
                })
                .setNegativeButton("Seguir como Anónimo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int x) {
                        usuarioIniciado.setText("Usuario: Anónimo");
                        Toast.makeText(MainActivity.this, "Has iniciado sesión como usuario anónimo", Toast.LENGTH_SHORT).show();
                    }
                })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            bundle = getIntent().getExtras();
            String usuario = bundle.getString("Nombre");
            usuarioIniciado.setText("Usuario: "+usuario);
        }

        cajaregalo = (CheckBox)findViewById(R.id.cajaregalo);
        tarjetadedicada = (CheckBox)findViewById(R.id.tarjetadedicada);

        peso=(EditText)findViewById(R.id.pesopaquete);

        AdaptadorZonas adaptador = new AdaptadorZonas(this);
        Zonas = (Spinner)findViewById(R.id.Zonas);
        Zonas.setAdapter(adaptador);

        tarifasRadio = (RadioGroup)findViewById(R.id.tarifas);


        tarifasRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.tarifanormal == tarifasRadio.getCheckedRadioButtonId()) {
                    urgencia = "Tarifa normal";
                } else {
                    urgencia = "Tarifa urgente";
                    precioExtra = precioExtra + (pr * 30) / 100;
                }
            }
        });

        cajaregalo.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cajaregalo.isChecked()){
                    preferencia += " con caja regalo ";
                }
                else{
                    preferencia = "ninguna";
                }
            }
        });

        tarjetadedicada.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tarjetadedicada.isChecked()) {
                    preferencia = " con tarjeta dedicatoria ";

                } else {
                    preferencia = "ninguna";

                }
            }
        });

        hacercalculos = (Button)findViewById(R.id.hacercalculos);
        hacercalculos.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(peso.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No ha introducido el peso del paquete", Toast.LENGTH_SHORT).show();
                }
                else{
                    int pesoo = Integer.parseInt(peso.getText().toString());

                    if(pesoo<=5){
                        precioExtra += pesoo;
                    }

                    else if(pesoo>=6 && pesoo<10){
                        precioExtra= precioExtra + pesoo*1.5;
                    }

                    else if(pesoo>10){
                        precioExtra = precioExtra + pesoo*2;
                    }

                    Intent intent = new Intent(MainActivity.this, PantallaEnvio.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("Zona", zon);
                    bundle.putString("Continente", cont);
                    bundle.putString("Preferencia", preferencia);
                    bundle.putDouble("Precio", pr);
                    bundle.putDouble("PrecioExtra", precioExtra);
                    bundle.putInt("Peso", pesoo);
                    bundle.putString("Urgencia", urgencia);
                    bundle.putInt("Imagen", img);
                    precioFinal = pr + precioExtra;
                    bundle.putDouble("Precio Final", precioFinal);
                    bundle.putString("Usuario", usuarioIniciado.getText().toString());
                    intent.putExtras(bundle);

                    precioExtra = 0;
                    pesoo=0;
                    urgencia = "";

                    for(int i=0; i < zonas.length; i++){
                        if(zonas[i].getZona().equals(zon)) {
                            pr = Double.parseDouble(zonas[i].getPrecio());
                        }
                    }
                    startActivity(intent);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.opcsesion:
                Intent login = new Intent(this, Login.class);
                startActivity(login);
                return true;
            case R.id.dibujo:
                Intent dibujo = new Intent(this,Dibujo.class);
                startActivity(dibujo);
                return true;
            case R.id.leerNoticias:
                Intent leerNoticias = new Intent(this, LeerNoticias.class);
                startActivity(leerNoticias);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class AdaptadorZonas extends ArrayAdapter<Zona> {
        Activity context;

        AdaptadorZonas(Activity context){
            super(context, R.layout.itemszonas,zonas);
            this.context = context;
        }


        public View getDropDownView(int position, View convertView, ViewGroup parent){
            return getView(position,convertView,parent);

        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.itemszonas, null);

            TextView lblZona = (TextView)convertView.findViewById(R.id.lblZona);
            lblZona.setText(zonas[position].getZona());

            TextView lblContinente = (TextView)convertView.findViewById(R.id.lblContinente);
            lblContinente.setText(zonas[position].getContinente());


            ImageView imagen = (ImageView)convertView.findViewById(R.id.Imagen);
            imagen.setImageResource(zonas[position].getImagen());

            TextView lblprecio = (TextView)convertView.findViewById(R.id.lblPrecio);
            lblprecio.setText(String.valueOf(zonas[position].getPrecio()));

            zon = zonas[position].getZona();
            cont = zonas[position].getContinente();
            img = zonas[position].getImagen();
            pr = Double.parseDouble(zonas[position].getPrecio());

            return convertView;
        }
    }
}

