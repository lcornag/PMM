package com.example.mati.basedatos;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class EjemploBaseDatosIni extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.lsclientes);

		listar();
	}

	public void listar() {
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "DBClientes", null, 1);
		SQLiteDatabase bd = cliBDh.getWritableDatabase();

		if (bd != null) {
			Cursor cursor;
			cursor = bd.rawQuery("SELECT * FROM Clientes", null);
			int cantidad = cursor.getCount();
			int i = 0;

			String[] clientes = new String[cantidad];

			if(cursor.moveToFirst()){
				do {
					String linea = cursor.getInt(0) + " - " + cursor.getString(1) + "\n" + cursor.getString(2);

					clientes[i] = linea;
					i++;
				} while (cursor.moveToNext());
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, clientes);
			ListView lista = (ListView) findViewById(R.id.lsclientes);
			lista.setAdapter(adapter);

			cursor.close();
			bd.close();

		}

	}
}