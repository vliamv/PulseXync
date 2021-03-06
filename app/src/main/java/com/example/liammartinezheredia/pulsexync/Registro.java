package com.example.liammartinezheredia.pulsexync;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Registro extends AppCompatActivity {

    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    }




    String nombre ;
    String email;
    String contra;
    String contra2;

    TextView nombretxt = (TextView) findViewById(R.id.nombretxt);
    TextView emailtxt = (TextView) findViewById(R.id.email);
    TextView contratxt = (TextView) findViewById(R.id.contra);
    TextView contra2txt = (TextView) findViewById(R.id.contra2);





    public void Regresarlogin(View miV){
    //te regresa a login
        Intent regresar = new Intent(this,Login.class);
        startActivity(regresar);
        finish();
    }

    public void SubirDatos(View miV){
        SQL_DB sqlAUX = new SQL_DB(this,"DB_Usuarios", null, 1);
         SQLiteDatabase db = sqlAUX.getWritableDatabase();

        nombre = nombretxt.getText().toString();
        email = emailtxt.getText().toString();
        contra = contratxt.getText().toString();


        String Nomdb = nombre;
        String Correodb = email;
        String Contradb = contra;

        ContentValues values = new ContentValues();
        values.put("Email", Correodb);
        values.put("Contrasena", Contradb);
        values.put("Nombre", Nomdb);

        db.insert("Usuarios",null,values);
        db.close();








        /*ContentValues datos = new ContentValues();
        datos.put("Nombre", nombre);
        datos.put("Email", email);
        datos.put("Contraseña", contra);

        if(db != null){
            try{
                db.insert("Usuarios", null, datos);

            } catch (Exception e){
                Toast.makeText(getApplicationContext(),
                        "Error en Insert " + e,
                        Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "No existe la base de datos",
                    Toast.LENGTH_LONG).show();
        }*/
    }

    public void validar(View miV){
        //valida que los campos no esten vacios y las contraseñas sean iguales
        //falta validar correo
        boolean nombrefull = false;
        boolean emailfull = false;
        boolean contrafull = false;
        boolean contraigual = false;

        nombre = nombretxt.getText().toString();
        email = emailtxt.getText().toString();
        contra = contratxt.getText().toString();
        contra2 = contra2txt.getText().toString();



        if(nombre.isEmpty()){
            nombretxt.setError("Escribe tu nombre de usuario");
        }else{
            nombrefull = true;
        }
        if(email.isEmpty()){
            emailtxt.setError("Escribe tu correo");

        }else{
            boolean auxmail = false;

            for(int i = 0; i<email.length(); ++i){
                if(email.charAt(i)=='@'){
                    for(int j = i+1; j<email.length(); ++j){
                        if(email.charAt(j)=='.'){
                            if(email.length()>=j+3){
                                if(email.charAt(j+1)=='c'){
                                    if(email.charAt(j+2)=='o'){
                                        if(email.charAt(j+3)=='m'){
                                            auxmail = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(auxmail==true){
                emailfull = true;
            }
            else{
                emailtxt.setError("Correo no valido");
            }
        }
        if (contra.isEmpty()){
            contratxt.setError("Escribe una contraseña");
        }else{
            contrafull = true;
        }
        if (contra.equals(contra2)){
            contraigual = true;
            CerrarTeclado(miV);

        }else{
            contra2txt.setError("La contraseña no coincide ");
            CerrarTeclado(miV);
        }

        if((nombrefull && emailfull && contrafull && contraigual) == true){
            CrearCuenta(miV);
        }else{
             Toast.makeText(this,"Llena los campos",Toast.LENGTH_SHORT).show();
        }




    }

    public void CerrarTeclado(View miV){
        InputMethodManager imm =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(miV.getWindowToken(), 0);
    }

    public void CrearCuenta(View miv){




        try {

            ProgressDialog progressDialog = new ProgressDialog(Registro.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setTitle("Creando cuenta...");
            progressDialog.setMessage("Por favor espere");
            progressDialog.show();
            SubirDatos(miv);//manda a llamar el metodo para subir datos


            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent entrar = new Intent(Registro.this,Menulateral.class);
                            startActivity(entrar);
                            finish();
                        }
                    }, 1500);




        }catch(Exception e){
            Toast.makeText(this, "Algo se murio", Toast.LENGTH_SHORT).show();

        }


    }
    /*public void MeterDatos(View miv){
        String fichero = "Usuarios.txt";

        String Correodb =  ;
        FileOutputStream fos;
        try {
            fos = openFileOutput(fichero, Context.MODE_PRIVATE);
            fos.write(Correodb.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),
                    "Error en Insert " + e,
                    Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Error en Insert " + e,
                    Toast.LENGTH_LONG).show();
        }
    }*/
}
