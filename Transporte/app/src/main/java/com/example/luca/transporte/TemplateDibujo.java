package com.example.luca.transporte;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class TemplateDibujo extends View {

    int cuadradoUnoIzqUp, cuadradoUnoIzqDown, cuadradoUnoDerechaUp, cuadradoUnoDerechaDown;

    public TemplateDibujo(Context context){
        super(context);
    }
    public TemplateDibujo(Context context, AttributeSet atributos){
        super(context, atributos);
    }

    protected void onDraw(Canvas lienzo){

        Paint miPincel = new Paint();

        miPincel.setStyle(Paint.Style.STROKE);
        miPincel.setColor(Color.BLACK);
        miPincel.setStrokeWidth(6);
        lienzo.drawRect(
                180,
                220,
                330,
                370,
                miPincel);
        super.onDraw(lienzo);

        miPincel.setColor(Color.BLACK);
        lienzo.drawRect(
                200,
                195,
                350,
                350,
                miPincel);
        super.onDraw(lienzo);

        lienzo.drawLine(180,220,200,195, miPincel);
        lienzo.drawLine(330,220,350,195, miPincel);
        lienzo.drawLine(180,370,200,350, miPincel);
        lienzo.drawLine(330,370,350,350, miPincel);
    }
}
