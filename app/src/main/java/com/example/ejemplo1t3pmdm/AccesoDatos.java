package com.example.ejemplo1t3pmdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class AccesoDatos {

    private Context contexto;
    Biblio miBD;

    public AccesoDatos(Context contexto){
        this.contexto = contexto;
        miBD = new Biblio(contexto);
    }

    public void ejemploMetodoConsultar(){
        SQLiteDatabase accesoLectura = miBD.getReadableDatabase();

        Cursor c = accesoLectura.rawQuery("SELECT * FROM Ejemplares", null);

        if (c.moveToFirst()){
            do {
                String titulo = c.getString(c.getColumnIndexOrThrow("titulo"));
                String autor  = c.getString(c.getColumnIndexOrThrow("autor"));
                Toast.makeText(contexto,
                        "Título: " + titulo + " | Autor: " + autor,
                        Toast.LENGTH_LONG).show();
            } while (c.moveToNext());
        } else {
            Toast.makeText(contexto,"No hay registros aún", Toast.LENGTH_LONG).show();
        }

        c.close();
    }

    public void ejemploMetodoModificacion(){
        SQLiteDatabase accesoEscritura = miBD.getWritableDatabase();

        /*
        try {
            accesoEscritura.execSQL(
                    "INSERT INTO Ejemplares(titulo, autor, anio, prestado) " +
                    "VALUES ('Los miserables','Soraya','2000', 0)"
            );

            Toast.makeText(contexto,
                    "Registro insertado correctamente mediante execSQL",
                    Toast.LENGTH_LONG).show();

            } catch (Exception e) {
            Toast.makeText(contexto,
                    "Error al insertar registro: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            }
        }
         */

        ContentValues registro = new ContentValues();
        registro.put("titulo","Los miserables");
        registro.put("autor","Soraya");
        registro.put("anio",2000);
        registro.put("prestado",0); // 0 = false, 1 = true

        long resultado = accesoEscritura.insert("Ejemplares", null, registro);

        if(resultado != -1){
            Toast.makeText(contexto,"Registro creado con id: "+resultado, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(contexto,"Error al insertar", Toast.LENGTH_LONG).show();
        }
    }
}
