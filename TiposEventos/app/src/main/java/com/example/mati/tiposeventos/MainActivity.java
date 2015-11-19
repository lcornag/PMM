package com.example.mati.tiposeventos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mati.tiposeventos.R;

public class MainActivity extends Activity {
    Context ctx=null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.gruporb);
        rg.clearCheck();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if( group.getCheckedRadioButtonId()==R.id.cmdRojo)
                    //
                if( group.getCheckedRadioButtonId()==R.id.cmdAzul)
                    //
                if (group.getCheckedRadioButtonId()==R.id.cmdAmarillo) {
                    //
                }
            }
        });
    }
}