package com.example.ejemplo1t3pmdm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    AccesoDatos baseBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseBD = new AccesoDatos(this);

        baseBD.ejemploMetodoModificacion();   // PRIMERO insertar
        baseBD.ejemploMetodoConsultar();      // LUEGO leer
    }
}
