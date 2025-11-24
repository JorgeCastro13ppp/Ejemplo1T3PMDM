package com.example.ejemplo1t3pmdm;

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

        try {
            Cursor c = accesoLectura.rawQuery("SELECT * FROM Ejemplares", null);

            if (c.moveToFirst()){
                do {
                    String titulo = c.getString(c.getColumnIndexOrThrow("titulo"));
                    String autor  = c.getString(c.getColumnIndexOrThrow("autor"));

                    Toast.makeText(contexto,
                            "Leyendo → " + titulo + " / " + autor,
                            Toast.LENGTH_LONG).show();

                } while (c.moveToNext());
            } else {
                Toast.makeText(contexto,"No hay registros", Toast.LENGTH_LONG).show();
            }

            c.close();
        } catch (Exception e){
            Toast.makeText(contexto,"Error al consultar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void ejemploMetodoModificacion(){
        SQLiteDatabase accesoEscritura = miBD.getWritableDatabase();

        try {
            accesoEscritura.execSQL(
                    "INSERT INTO Ejemplares (titulo,autor,anio,prestado) " +
                            "VALUES ('Los miserables','Soraya','2000',0)"
            );

            Toast.makeText(contexto,
                    "Registro insertado correctamente",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e){
            Toast.makeText(contexto,
                    "Error insertando registro: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
