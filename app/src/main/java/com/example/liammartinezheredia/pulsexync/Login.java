package com.example.liammartinezheredia.pulsexync;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    boolean emailfull = false;
    boolean contrafull = false;

    String email;
    String contra;


    public void Validar (View miV){

        //verifica que esten llenos los campos

        TextView Emailtxt =  (TextView) findViewById(R.id.email);
        email = Emailtxt.getText().toString();
        TextView Contratxt =  (TextView) findViewById(R.id.contraseña);
        contra = Contratxt.getText().toString();

        if (email.isEmpty()) {
            Emailtxt.setError("Escribe tu Email");
            //Toast.makeText(this, "Escribe tu correo", Toast.LENGTH_SHORT).show();

        } else {
            emailfull = true;


        }
        if (contra.isEmpty()) {
            Contratxt.setError("Escribe tu contraseña");
            //Toast.makeText(this, "Escribe tu contraseña", Toast.LENGTH_SHORT).show();

        } else {
            contrafull = true;


        }
        if ((emailfull && contrafull) == true) {
            Entrar(miV);
        }

    }

    public void Entrar (View miV){



        try {

            ProgressDialog progressDialog = new ProgressDialog(Login.this);
            progressDialog.setTitle("Autentificando datos...");
            progressDialog.setMessage("Por favor espere");
            progressDialog.show();

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent Entrar = new Intent(Login.this, Inicio.class);
                            startActivity(Entrar);
                            //finish();
                        }
                    }, 2000);




        }catch(Exception e){
            Toast.makeText(this, "Algo se murio", Toast.LENGTH_SHORT).show();

        }

    }

    public void NuevaCuenta (View miV){

        Intent Nuevousuario = new Intent(this,Registro.class);
        startActivity(Nuevousuario);
        finish();
    }


}
