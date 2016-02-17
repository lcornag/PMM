package com.example.luca.solobici;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferencia {

    public class PantallaOpciones extends PreferenceActivity {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.layout.preferencias);
        }
    }
}