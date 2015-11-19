package com.example.mati.menuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Toast.makeText(this, "Opcion 1 pulsada!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnuOpc2:
                Toast.makeText(this, "Opcion 2 pulsada!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnuOpc3:
                Toast.makeText(this, "Opcion 3 pulsada!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SubMnuOpc1:
                Toast.makeText(this, "Submenu: Opcion 1!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SubMnuOpc2:
                Toast.makeText(this, "Submenu: Opcion 2!", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
