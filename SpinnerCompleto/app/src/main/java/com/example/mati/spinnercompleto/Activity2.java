package com.example.mati.spinnercompleto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        final TextView compraRealizada = (TextView)findViewById(R.id.cr);
        compraRealizada.setText("Compra Realizada");

    }
}