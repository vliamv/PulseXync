package com.example.liammartinezheredia.pulsexync;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


    }


    String nombre ;
    String email;
    String contra;
    String contra2;



    public void Regresarlogin(View miV){
    //te regresa a login
        Intent regresar = new Intent(this,Login.class);
        startActivity(regresar);
        finish();
    }

    public void SubirDatos(View miV){
        SQL_DB sqlAUX = new SQL_DB(this,"DB_Usuarios", null, 1);
        final SQLiteDatabase db = sqlAUX.getWritableDatabase();

        ContentValues datos = new ContentValues();
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
        }
    }

    public void validar(View miV){
        //valida que los campos no esten vacios y las contraseñas sean iguales
        //falta validar correo
        boolean nombrefull = false;
        boolean emailfull = false;
        boolean contrafull = false;
        boolean contraigual = false;

        TextView nombretxt = (TextView) findViewById(R.id.nombretxt);
        nombre = nombretxt.getText().toString();
        TextView emailtxt = (TextView) findViewById(R.id.email);
        email = emailtxt.getText().toString();
        TextView contratxt = (TextView) findViewById(R.id.contra);
        contra = contratxt.getText().toString();
        TextView contra2txt = (TextView) findViewById(R.id.contra2);
        contra2 = contra2txt.getText().toString();



        if(nombre.isEmpty()){
            nombretxt.setError("Escribe tu nombre de usuario");
        }else{
            nombrefull = true;
        }
        if(email.isEmpty()){
            emailtxt.setError("Escribe tu correo");

        }else{
            emailfull = true;
        }
        if (contra.isEmpty()){
            contratxt.setError("Escribe una contraseña");
        }else{
            contrafull = true;
        }
        if (contra.equals(contra2)){
            contraigual = true;
        }else{
            contra2txt.setError("La contraseña no coincide ");
        }

        if((nombrefull && emailfull && contrafull && contraigual) == true){
            CrearCuenta(miV);
        }else{
             Toast.makeText(this,"Llena los campos",Toast.LENGTH_SHORT).show();
        }




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
                            Intent entrar = new Intent(Registro.this,Inicio.class);
                            startActivity(entrar);
                            //finish();
                        }
                    }, 2500);




        }catch(Exception e){
            Toast.makeText(this, "Algo se murio", Toast.LENGTH_SHORT).show();

        }


    }
}
