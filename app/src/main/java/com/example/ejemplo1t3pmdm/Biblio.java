package com.example.ejemplo1t3pmdm;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Biblio extends SQLiteOpenHelper {

    Context contexto;

    static String createBDSQL = "CREATE TABLE Ejemplares (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "titulo TEXT," +
            "autor TEXT," +
            "anio TEXT," +
            "prestado BOOLEAN)";     // BOOLEAN ⇢ INTEGER (0/1)

    public Biblio(Context context){
        super(context,"BDBiblioteca",null,1);
        contexto=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(createBDSQL);
            Toast.makeText(contexto,"Base de datos creada correctamente", Toast.LENGTH_LONG).show();
        }catch (SQLException e){
            Toast.makeText(contexto,"Error al crear la BD: "+e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE IF EXISTS Ejemplares");
            onCreate(db);
            Toast.makeText(contexto,"BD actualizada", Toast.LENGTH_LONG).show();
        }catch(SQLException e){
            Toast.makeText(contexto,"Error al actualizar BD: "+e,Toast.LENGTH_LONG).show();
        }
    }
}
