package com.example.ejercicio2pmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button botonManejador;
    private Button botonVisualizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonManejador = (Button) findViewById(R.id.buttonAdd);
        botonVisualizador = (Button) findViewById(R.id.buttonView);
        botonManejador.setOnClickListener(this);
        botonVisualizador.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAdd:
                Intent intentManejador = new Intent(getApplicationContext(),ManejadorAgenda.class);
                startActivity(intentManejador);
                break;
            case R.id.buttonView:
                Intent intentVisualizador = new Intent(getApplicationContext(),VisualizadorAgenda.class);
                startActivity(intentVisualizador);
                break;
        }
    }
}