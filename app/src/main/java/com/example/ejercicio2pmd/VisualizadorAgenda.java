package com.example.ejercicio2pmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VisualizadorAgenda extends AppCompatActivity implements View.OnClickListener {

    private final String NOMBRE_DB = "agenda";
    private final int VERSION = 1;

    private MiBaseDatos miBaseDatos;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor miCursor;

    private Button botonVolver;
    private Button botonFirst;
    private Button botonPrevio;
    private Button botonNext;
    private Button botonLast;

    private EditText eTid;
    private EditText eTnombre;
    private EditText eTapellidos;
    private EditText eTtelefono;
    private EditText eTfechaNac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador_agenda);

        miBaseDatos = new MiBaseDatos(getApplicationContext(), NOMBRE_DB, null, VERSION);
        sqLiteDatabase = miBaseDatos.getWritableDatabase();
        miCursor = sqLiteDatabase.rawQuery("SELECT * FROM contacto",null);

        eTid = (EditText) findViewById(R.id.eTid);
        eTnombre = (EditText) findViewById(R.id.eTnombre);
        eTapellidos = (EditText) findViewById(R.id.eTapellidos);
        eTtelefono = (EditText) findViewById(R.id.eTtelefono);
        eTfechaNac = (EditText) findViewById(R.id.eTfechaNac);

        botonVolver = (Button) findViewById(R.id.buttonBack);
        botonFirst = (Button) findViewById(R.id.buttonFirst);
        botonPrevio = (Button) findViewById(R.id.buttonPrevius);
        botonNext = (Button) findViewById(R.id.buttonNext);
        botonLast = (Button) findViewById(R.id.buttonLast);

        botonVolver.setOnClickListener(this);
        botonFirst.setOnClickListener(this);
        botonPrevio.setOnClickListener(this);
        botonNext.setOnClickListener(this);
        botonLast.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonFirst:
                mostrarPrimero();
                break;
            case R.id.buttonPrevius:
                mostrarPrevio();
                break;
            case R.id.buttonNext:
                mostrarSiguiente();
                break;
            case R.id.buttonLast:
                mostrarUltimo();
                break;
            case R.id.buttonBack:
                Intent menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(menu);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        miCursor.close();
    }

    private void mostrarPrimero() {
        if(miCursor.moveToFirst()){
            eTid.setText(String.valueOf(miCursor.getInt(0)));
            eTnombre.setText(miCursor.getString(1));
            eTapellidos.setText(miCursor.getString(2));
            eTtelefono.setText(miCursor.getString(3));
            eTfechaNac.setText(miCursor.getString(4));
        }
    }

    private void mostrarPrevio() {
        if(miCursor.moveToPrevious()){
            eTid.setText(String.valueOf(miCursor.getInt(0)));
            eTnombre.setText(miCursor.getString(1));
            eTapellidos.setText(miCursor.getString(2));
            eTtelefono.setText(miCursor.getString(3));
            eTfechaNac.setText(miCursor.getString(4));
        }
    }

    private void mostrarSiguiente() {
        if(miCursor.moveToNext()){
            eTid.setText(String.valueOf(miCursor.getInt(0)));
            eTnombre.setText(miCursor.getString(1));
            eTapellidos.setText(miCursor.getString(2));
            eTtelefono.setText(miCursor.getString(3));
            eTfechaNac.setText(miCursor.getString(4));
        }
    }

    private void mostrarUltimo() {
        if(miCursor.moveToLast()){
            eTid.setText(String.valueOf(miCursor.getInt(0)));
            eTnombre.setText(miCursor.getString(1));
            eTapellidos.setText(miCursor.getString(2));
            eTtelefono.setText(miCursor.getString(3));
            eTfechaNac.setText(miCursor.getString(4));
        }
    }
}