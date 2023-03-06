package com.example.ejercicio2pmd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ManejadorAgenda extends AppCompatActivity implements View.OnClickListener {

    private final String NOMBRE_DB = "agenda";
    private final int VERSION = 1;

    private MiBaseDatos miBaseDatos;
    private SQLiteDatabase sqLiteDatabase;

    private Button botonVolver;
    private Button botonaddContact;

    private EditText eTnombre;
    private EditText eTapellidos;
    private EditText eTtelefono;
    private EditText eTfechaNac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manejador_agenda);

        miBaseDatos = new MiBaseDatos(getApplicationContext(), NOMBRE_DB, null, VERSION);
        sqLiteDatabase = miBaseDatos.getWritableDatabase();

        botonVolver = (Button) findViewById(R.id.buttonBack);
        botonaddContact = (Button) findViewById(R.id.buttonAddContact);
        botonVolver.setOnClickListener(this);
        botonaddContact.setOnClickListener(this);

        eTnombre = (EditText) findViewById(R.id.eTnombre);
        eTapellidos = (EditText) findViewById(R.id.eTapellidos);
        eTtelefono = (EditText) findViewById(R.id.eTtelefono);
        eTfechaNac = (EditText) findViewById(R.id.eTfechaNac);
        eTfechaNac.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddContact:
                addContactInDB();
                break;
            case R.id.buttonBack:
                Intent menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(menu);
                break;
            case R.id.eTfechaNac:
                showCalendar();
                break;
        }
    }

    private void showCalendar() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialogo = new DatePickerDialog(ManejadorAgenda.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                eTfechaNac.setText(dayOfMonth+"/"+month+"/"+year);
            }
        },year,month,day);

        dialogo.show();

    }

    private void addContactInDB() {
        String nombre = eTnombre.getText().toString();
        String apellidos = eTapellidos.getText().toString();
        String telefono = eTtelefono.getText().toString();
        String fechaNac = eTfechaNac.getText().toString();

        if (nombre.isEmpty() && apellidos.isEmpty() && telefono.isEmpty() && fechaNac.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Introduce datos en campos", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues newContact = new ContentValues();
            newContact.put("nombre", nombre);
            newContact.put("apellidos", apellidos);
            newContact.put("telefono", telefono);
            newContact.put("fechaNacimiento", fechaNac);

            sqLiteDatabase.insert("contacto", null, newContact);
            Toast.makeText(getApplicationContext(), "Contacto añadido", Toast.LENGTH_SHORT).show();

            eTnombre.setText("");
            eTapellidos.setText("");
            eTtelefono.setText("");
            eTfechaNac.setText("");

        }
    }
}