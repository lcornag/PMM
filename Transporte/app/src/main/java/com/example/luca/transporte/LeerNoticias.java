package com.example.luca.transporte;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeerNoticias extends AppCompatActivity {

    private static final String TAG="Http conexion";
    private Button boton;
    private TextView texto;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leer_noticias);
        boton = (Button) findViewById(R.id.boton);
        texto = (TextView) findViewById(R.id.texto);

        final String urlS = "http://www.elpais.com/rss/feed.html?feedId=1022";
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new AsyncHttpTask().execute(urlS);
            }
        });

    }

    public  class AsyncHttpTask extends AsyncTask<String, String, String> {
        String response="";

        private void checkInternetConenction(){

            ConnectivityManager check = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if (check != null){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Network[] networks = check.getAllNetworks();
                    for (Network network : networks) {
                        NetworkInfo info = check.getNetworkInfo(network);


                        if (info != null) {
                            if (info.getState() == NetworkInfo.State.CONNECTED) {
                                Toast.makeText(LeerNoticias.this, "Internet is connected",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                else {
                    NetworkInfo[] info = check.getAllNetworkInfo();
                    if (info != null){
                        for (int i = 0; i < info.length; i++) {
                            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                                Toast.makeText(LeerNoticias.this, "Internet is connected",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
            else{
                Toast.makeText(LeerNoticias.this, "not conencted to internet",
                        Toast.LENGTH_SHORT).show();
            }
        }

        protected void onPreExecute(){
            checkInternetConenction();
        }

        protected String doInBackground(String... params) {
            InputStream inputStream = null;

            HttpURLConnection urlConnection = null;

            String salida = "";
            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);

                urlConnection.connect();
                if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                    InputStream is =  urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    int i = 0;
                    int j = 0;
                    String linea = reader.readLine();
                    while (linea != null) {
                        if (linea.indexOf("<title>") >= 0) {
                            i = linea.indexOf("<title>") + 16;
                            j = linea.indexOf("</title>") - 3;
                            salida += linea.substring(i, j);
                            salida += "\n----------------\n";
                        }
                        linea = reader.readLine();
                    }
                    reader.close();
                    if (is != null) {
                        is.close();
                    }
                }
                publishProgress(salida);
            }catch(Exception e){
                salida= new String("Exception: " + e.getMessage());
            }
            finally {
                urlConnection.disconnect();
            }
            return salida;
        }
        protected void onProgressUpdate(String... pasos) {
            texto.append(pasos[0]);
        }
        protected void onPostExecute(){}
    }
}