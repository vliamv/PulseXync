package com.example.liammartinezheredia.pulsexync;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Login extends AppCompatActivity {
    private Cursor fila;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }




    boolean emailfull = false;
    boolean contrafull = false;

    String email;
    String contra;

    TextView Emailtxt =  (TextView) findViewById(R.id.email);
    TextView Contratxt =  (TextView) findViewById(R.id.contraseña);

    public void Validar (View miV){

//

        //verifica que esten llenos los campos


        email = Emailtxt.getText().toString();
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

        TomarDatos(miV);

        try {

            ProgressDialog progressDialog = new ProgressDialog(Login.this);

            progressDialog.setTitle("Autentificando datos...");
            progressDialog.setMessage("Por favor espere");
            progressDialog.show();

            TomarDatos(miV);


            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {





                            //*/


                            //llamarWS loginTH = new llamarWS();
                            //loginTH.execute();



                            //Intent Entrar = new Intent(Login.this, Menulateral.class);
                            //Creamos la información a pasar entre actividades
                            //Bundle mandar = new Bundle();
                            //mandar.putString("Correo", email);

                            //Añadimos la información al intent
                            //Entrar.putExtras(mandar);


                            //startActivity(Entrar);
                            //finish();
                            ///*
                        }
                    }, 1500);







        }catch(Exception e){
            Toast.makeText(this, "Algo se murio", Toast.LENGTH_SHORT).show();

        }

    }

    public void NuevaCuenta (View miV){

        Intent Nuevousuario = new Intent(this,Registro.class);
        startActivity(Nuevousuario);
        finish();
    }

    private class llamarWS extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... params) {
// TODO: attempt authentication against a network service.
//WebService - Opciones

            setContentView(R.layout.activity_login);


            String NAMESPACE = "http://urn:BuscarAlumno/";
            String URL="http://http://172.20.10.4//BuscarAlumno.wsdl";
            String METHOD_NAME = "getInfo";
            String SOAP_ACTION = "http://urn:BuscarAlumno/getInfo";


            String resultadoWS;
            Boolean resultAUX = false;



            //View miV = new View();



            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("emailws", email);
            request.addProperty("contraseñaws", contra);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);


            HttpTransportSE ht = new HttpTransportSE(URL);
            try {
                ht.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                resultadoWS=response.toString();
                if(resultadoWS == "pasa"){
                    resultAUX= true;
                }else{
                    resultAUX = false;
                }

                if(resultAUX == true){
                    Intent Entrar = new Intent(Login.this, Menulateral.class);

                    //Bundle mandar = new Bundle();
                    //mandar.putString("Correo", email);

                    //Entrar.putExtras(mandar);

                    startActivity(Entrar);
                    finish();
                }else{
                    CamposIncorrectos();

                }
                //Log.i("Resultado: ",resultadoWS);
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
                Toast.makeText(getApplicationContext(), "Error datos incorrectos", Toast.LENGTH_LONG).show();

            }
            else{
                Intent Entrar = new Intent(Login.this, Menulateral.class);
                //Entrar.putExtra("Email",email);
                startActivity(Entrar);
                finish();

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }

        public void CamposIncorrectos (){

            TextView Emailtxt =  (TextView) findViewById(R.id.email);
            Emailtxt.setError("Correo o contraseña incorrectos");
            Emailtxt.setText("");
            TextView Contratxt =  (TextView) findViewById(R.id.contraseña);
            Contratxt.setText("");


        }
    }


    public void CamposIncorrectos (View miV){

        TextView Emailtxt =  (TextView) findViewById(R.id.email);
        Emailtxt.setError("Correo o contraseña incorrectos");
        Emailtxt.setText("");
        TextView Contratxt =  (TextView) findViewById(R.id.contraseña);
        Contratxt.setText("");


    }

    public void TomarDatos(View miV) {

        email = Emailtxt.getText().toString();
        contra = Contratxt.getText().toString();

        SQL_DB sqlAUX = new SQL_DB(this, "DB_Usuarios", null, 1);
        SQLiteDatabase db = sqlAUX.getWritableDatabase();


        String Correodb = email;
        String Contradb = contra;

        fila = db.rawQuery("select Email,Contrasena from Usuarios where Email='"+email+"' and Contrasena='"+contra+"' ",null);

        while (fila.moveToFirst()==true){
            String emai=fila.getString(0);
            String pass=fila.getString(1);
            if(email.equals(emai) && contra.equals(pass)){
                Intent Entrar = new Intent(Login.this, Menulateral.class);
                startActivity(Entrar);

            }
            else
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void PDialog() {
        progressDialog = new ProgressDialog(this,R.style.CustomDialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Logging in. Please wait.");
        progressDialog.show();
    }*/


}
