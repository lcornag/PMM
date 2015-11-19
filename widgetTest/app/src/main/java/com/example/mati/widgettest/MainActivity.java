package com.example.mati.widgettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chkBoxCycling;
    CheckBox chkBoxRunning;
    CheckBox chkBoxSwimming;
    CheckBox chkBoxGaming;
    TextView txtHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialUISetup();
    }

    public void initialUISetup() {
        chkBoxCycling = (CheckBox) findViewById(R.id.chkBoxCycling);
        chkBoxRunning = (CheckBox) findViewById(R.id.chkBoxRunning);
        chkBoxSwimming = (CheckBox) findViewById(R.id.chkBoxSwimming);
        chkBoxGaming = (CheckBox) findViewById(R.id.chkBoxGaming);

        chkBoxCycling.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxRunning.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxSwimming.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxGaming.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
    }

    class myCheckBoxChangeClicker implements CheckBox.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                if(buttonView==chkBoxCycling) {
                    showTextNotification("Cycling");
                }
                if(buttonView==chkBoxRunning) {
                    showTextNotification("Running");
                }
                if(buttonView==chkBoxSwimming) {
                    showTextNotification("Swimming");
                }
                if(buttonView==chkBoxGaming) {
                    showTextNotification("Gaming");
                }
            }
        }
    }   // clase interna

    public void showTextNotification(String msgToDisplay) {
        //txtHobby.setText(msgToDisplay);
        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }
}