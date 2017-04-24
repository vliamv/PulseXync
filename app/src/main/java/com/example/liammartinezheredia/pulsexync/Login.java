package com.example.liammartinezheredia.pulsexync;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
/*import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;*/

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

            //llamarWS loginTH = new llamarWS();
           // loginTH.execute();

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {

                            Intent Entrar = new Intent(Login.this, Menulateral.class);
                            //Entrar.putExtra("Email",email);
                            startActivity(Entrar);
                            finish();
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

    /*private class llamarWS extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... params) {
// TODO: attempt authentication against a network service.
//WebService - Opciones
            String NAMESPACE = "http://ws/";
            String URL="http://148.204.168.11:8080/WebApplication6/wsTest?WSDL";
            String METHOD_NAME = "getInfo";
            String SOAP_ACTION = "http://ws/hello";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("name", xx);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);


            HttpTransportSE ht = new HttpTransportSE(URL);
            try {
                ht.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                resultado=response.toString();

                Log.i("Resultado: ",resultado);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if(success==false){
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(getApplicationContext(), "El resultado es: "+resultado, Toast.LENGTH_LONG).show();
                txt.setText(resultado);
                ///es la interaccion

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
    }*/


    /*private void PDialog() {
        progressDialog = new ProgressDialog(this,R.style.CustomDialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Logging in. Please wait.");
        progressDialog.show();
    }*/


}
