package com.example.liammartinezheredia.pulsexync;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
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

//

        //verifica que esten llenos los campos

        TextView Emailtxt =  (TextView) findViewById(R.id.email);
        email = Emailtxt.getText().toString();
        TextView Contratxt =  (TextView) findViewById(R.id.contraseña);
        contra = Contratxt.getText().toString();

        if (email.isEmpty()) {
            Emailtxt.setError("Escribe tu Email");
            //Toast.makeText(this, "Escribe tu correo", Toast.LENGTH_SHORT).show();

        } else {
            try{
                for(int i = 0; i<email.length(); ++i){
                    if(email.charAt(i)=='@'){
                        for(int j = i+1; j<email.length(); ++j){
                            if(email.charAt(j)=='.'){
                                if(email.length()>=j+3){
                                    if(email.charAt(j+1)=='c'){
                                        if(email.charAt(j+2)=='o'){
                                            if(email.charAt(j+3)=='m'){
                                                emailfull = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch(Exception e){
                Emailtxt.setError("Correo no valido");
            }




        }
        if (contra.isEmpty()) {
            Contratxt.setError("Escribe tu contraseña");
            //Toast.makeText(this, "Escribe tu contraseña", Toast.LENGTH_SHORT).show();
            CerrarTeclado(miV);

        } else {
            contrafull = true;
            CerrarTeclado(miV);

        }
        if ((emailfull && contrafull) == true) {
            Entrar(miV);
        }

    }

    public void CerrarTeclado(View miV){
        InputMethodManager imm =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(miV.getWindowToken(), 0);
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
    /*private void PDialog() {
        progressDialog = new ProgressDialog(this,R.style.CustomDialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Logging in. Please wait.");
        progressDialog.show();
    }*/


}
