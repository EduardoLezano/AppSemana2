package com.atuksystems.aplicacionsemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmDataActivity extends AppCompatActivity {
    private TextView tvDatos;

    private String nombre;
    private String telefono;
    private String email;
    private String descripcion;
    private String fecha;
    private int year;
    private int mes;
    private int dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_data);

        tvDatos = findViewById(R.id.tvDatos);

        nombre = getIntent().getStringExtra(MainActivity.EXTRA_NOMBRE);
        fecha = getIntent().getStringExtra(MainActivity.EXTRA_FECHA);
        telefono = getIntent().getStringExtra(MainActivity.EXTRA_TELEFONO);
        email = getIntent().getStringExtra(MainActivity.EXTRA_EMAIL);
        descripcion = getIntent().getStringExtra(MainActivity.EXTRA_DESCRIPCION);
        year = getIntent().getIntExtra(MainActivity.EXTRA_YEAR,1980);
        mes = getIntent().getIntExtra(MainActivity.EXTRA_MES,1);
        dia = getIntent().getIntExtra(MainActivity.EXTRA_DIA,1);
        String datos = String.format("%s\n%s\n%s\n%s\n\n%s\n"
                , nombre
                , fecha
                , telefono
                , email
                , descripcion);

        tvDatos.setText(datos);
    }

    public void editarDatos(View view){
        Intent intent = new Intent(this,MainActivity.class);

        intent.putExtra(MainActivity.EXTRA_NOMBRE, nombre);
        intent.putExtra(MainActivity.EXTRA_TELEFONO, telefono);
        intent.putExtra(MainActivity.EXTRA_EMAIL, email);
        intent.putExtra(MainActivity.EXTRA_DESCRIPCION, descripcion);
        intent.putExtra(MainActivity.EXTRA_FECHA, fecha);
        intent.putExtra(MainActivity.EXTRA_DIA,dia);
        intent.putExtra(MainActivity.EXTRA_MES,mes);
        intent.putExtra(MainActivity.EXTRA_YEAR,year);

        startActivity(intent);
    }
}