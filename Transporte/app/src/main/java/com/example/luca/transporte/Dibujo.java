package com.example.luca.transporte;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;

public class Dibujo extends Activity {
    private static TemplateDibujo TempDibujo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dibujo);
        TempDibujo = (TemplateDibujo) findViewById(R.id.areadibujo);
    }
}
