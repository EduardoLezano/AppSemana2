package com.atuksystems.aplicacionsemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NOMBRE = "extraNombre";
    public static final String EXTRA_TELEFONO = "extraTelefono";
    public static final String EXTRA_EMAIL = "extraEmail";
    public static final String EXTRA_DESCRIPCION = "extraDescripcion";
    public static final String EXTRA_FECHA = "extraFecha";
    public static final String EXTRA_DIA = "extraDia";
    public static final String EXTRA_MES = "extraMes";
    public static final String EXTRA_YEAR = "extrYear";

    private TextInputLayout textInputNombre;
    private TextInputLayout textInputTelefono;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputDescripcion;
    private DatePicker datePickerFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputNombre = findViewById(R.id.tilNombre);
        textInputTelefono = findViewById(R.id.tilTelefono);
        textInputEmail = findViewById(R.id.tilMail);
        textInputDescripcion = findViewById(R.id.tilDescripcion);
        datePickerFecha = findViewById(R.id.dpFecha);

    }

    @Override
    protected void onResume() {
        super.onResume();
        textInputNombre.getEditText().setText( getIntent().getStringExtra(EXTRA_NOMBRE));
        textInputTelefono.getEditText().setText( getIntent().getStringExtra(EXTRA_TELEFONO));
        textInputEmail.getEditText().setText( getIntent().getStringExtra(EXTRA_EMAIL));
        textInputDescripcion.getEditText().setText( getIntent().getStringExtra(EXTRA_DESCRIPCION));
        datePickerFecha.updateDate(
                getIntent().getIntExtra(EXTRA_YEAR,1980) ,
                getIntent().getIntExtra(EXTRA_MES,1) ,
                getIntent().getIntExtra(EXTRA_DIA,1)
        );
    }

    public void siguiente(View view) {

        if (!validarEmail() | !validarNombre() | !validarTelefono() | !validarDescripcion()) {
            return;
        }

        String nombre = textInputNombre.getEditText().getText().toString().trim();
        String telefono = textInputTelefono.getEditText().getText().toString().trim();
        String email = textInputEmail.getEditText().getText().toString().trim();
        String descripcion = textInputDescripcion.getEditText().getText().toString().trim();
        int dia = datePickerFecha.getDayOfMonth();
        int mes = datePickerFecha.getMonth();
        int year = datePickerFecha.getYear();

        String fecha = dia + "//" + (int)(mes+1)  + "//" + year;

        Intent intent = new Intent(this, ConfirmDataActivity.class);
        intent.putExtra(EXTRA_NOMBRE, nombre);
        intent.putExtra(EXTRA_TELEFONO, telefono);
        intent.putExtra(EXTRA_EMAIL, email);
        intent.putExtra(EXTRA_DESCRIPCION, descripcion);
        intent.putExtra(EXTRA_FECHA, fecha);
        intent.putExtra(EXTRA_DIA,dia);
        intent.putExtra(EXTRA_MES,mes);
        intent.putExtra(EXTRA_YEAR,year);

        startActivity(intent);
    }

    private boolean validarNombre() {
        String nombreInput = textInputNombre.getEditText().getText().toString().trim();
        if (nombreInput.isEmpty()) {
            textInputNombre.setError("Este campo no puede estar vacio");
            return false;
        } else if (nombreInput.length() > 15) {
            textInputNombre.setError("Nombre muy largo");
            return false;
        } else {
            textInputNombre.setError(null);
            return true;
        }

    }

    private boolean validarTelefono() {
        String telefonoInput = textInputTelefono.getEditText().getText().toString().trim();
        if (telefonoInput.isEmpty()) {
            textInputTelefono.setError("Este campo no puede estar vacio");
            return false;
        } else {
            textInputTelefono.setError(null);
            return true;
        }
    }

    private boolean validarEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Este campo no puede estar vacio");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validarDescripcion() {
        String descripcionInput = textInputDescripcion.getEditText().getText().toString().trim();
        if (descripcionInput.isEmpty()) {
            textInputDescripcion.setError("Este campo no puede estar vacio");
            return false;
        } else {
            textInputDescripcion.setError(null);
            return true;
        }
    }
}