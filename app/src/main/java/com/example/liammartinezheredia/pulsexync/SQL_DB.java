package com.example.liammartinezheredia.pulsexync;

/**
 * Created by liammartinezheredia on 14/04/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class SQL_DB extends SQLiteOpenHelper{



    String Tabla_BD = "CREATE TABLE Usuarios( Email VARCHAR primary key, Contrasena TEXT,Nombre TEXT)";


    public SQL_DB(Context contexto, String DB_usuarios, CursorFactory factory, int version){
        super(contexto, DB_usuarios, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase BasedeDatos) {

        BasedeDatos.execSQL(Tabla_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase BasedeDatos, int oldVersion, int newVersion) {

        BasedeDatos.execSQL("DROP TABLE IF EXISTS Usuarios");
        BasedeDatos.execSQL(Tabla_BD);

    }
}

