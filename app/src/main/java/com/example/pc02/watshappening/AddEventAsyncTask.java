package com.example.pc02.watshappening;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pc02 on 25/10/2016.
 */

public class AddEventAsyncTask extends AsyncTask<Void, Void, String>{

    String id;
    HttpURLConnection urlConnection;
    String nameAct = "AsyncTask";
    Context context;
    ProgressBar progressBar;
    static boolean haencontrado = false;

    public AddEventAsyncTask(String id, Context context, ProgressBar progressBar){
        this.id = id;
        this.context = context;
        this.progressBar= progressBar;
    }


    @Override
    protected void onPreExecute(){
        MyLog.d("AsyncTask", "onPreExecute...");
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder cadenaAcontecimiento = new StringBuilder();
        MyLog.d("AsyncTask", "doInBackground...");
        try {
            URL url = new URL("http://eventop.hol.es/api/v1/acontecimiento/"+id);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            MyLog.d("AsyncTask", "Acceso correcto");
            String line;
            while ((line = reader.readLine()) != null) {
                cadenaAcontecimiento.append(line);

            }

        }catch( Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Imposible conectar al servidor", Toast.LENGTH_SHORT).show();
        }
        finally {
            urlConnection.disconnect();
            MyLog.d("AsyncTask", "disconnected to INTERNET...");
        }

       // MyLog.d("AsyncTask", cadenaAcontecimiento.toString());


        try {
            JSONObject jsonRaiz = new JSONObject(cadenaAcontecimiento.toString());

            if(jsonRaiz.has("acontecimiento")){
                MyLog.i(nameAct, "Acontecimiento encontrado!");
                haencontrado = true;

                JSONObject jsonAcontecimiento = new JSONObject(jsonRaiz.getString("acontecimiento"));

                String id = (jsonAcontecimiento.has("id")) ? jsonAcontecimiento.getString("id"): "";
                String nombre = (jsonAcontecimiento.has("nombre")) ? jsonAcontecimiento.getString("nombre"): "";
                String organizador = (jsonAcontecimiento.has("organizador")) ? jsonAcontecimiento.getString("organizador"): "";
                String descripcion = (jsonAcontecimiento.has("descripcion")) ? jsonAcontecimiento.getString("descripcion"): "";
                String tipo = (jsonAcontecimiento.has("tipo")) ? jsonAcontecimiento.getString("tipo"): "";
                String portada = (jsonAcontecimiento.has("portada")) ? jsonAcontecimiento.getString("portada"): "";
                String inicio = (jsonAcontecimiento.has("inicio")) ? jsonAcontecimiento.getString("inicio"): "";
                String fin = (jsonAcontecimiento.has("fin")) ? jsonAcontecimiento.getString("fin"): "";
                String direccion = (jsonAcontecimiento.has("direccion")) ? jsonAcontecimiento.getString("direccion"): "";
                String localidad = (jsonAcontecimiento.has("localidad")) ? jsonAcontecimiento.getString("localidad"): "";
                String cod_postal = (jsonAcontecimiento.has("cod_postal")) ? jsonAcontecimiento.getString("cod_postal"): "";
                String provincia = (jsonAcontecimiento.has("provincia")) ? jsonAcontecimiento.getString("provincia"): "";
                String longitud = (jsonAcontecimiento.has("longitud")) ? jsonAcontecimiento.getString("longitud"): "";
                String latitud = (jsonAcontecimiento.has("latitud")) ? jsonAcontecimiento.getString("latitud"): "";
                String telefono = (jsonAcontecimiento.has("telefono")) ? jsonAcontecimiento.getString("telefono"): "";
                String email = (jsonAcontecimiento.has("email")) ? jsonAcontecimiento.getString("email"): "";
                String web = (jsonAcontecimiento.has("web")) ? jsonAcontecimiento.getString("web"): "";
                String facebook = (jsonAcontecimiento.has("facebook")) ? jsonAcontecimiento.getString("facebook"): "";
                String twitter = (jsonAcontecimiento.has("twitter")) ? jsonAcontecimiento.getString("twitter"): "";
                String instagram = (jsonAcontecimiento.has("instagram")) ? jsonAcontecimiento.getString("instagram"): "";

                ContentValues nuevoRegistroA = new ContentValues();

                nuevoRegistroA.put("id", id);
                nuevoRegistroA.put("nombre", nombre);
                nuevoRegistroA.put("organizador", organizador);
                nuevoRegistroA.put("descripcion", descripcion);
                nuevoRegistroA.put("tipo", tipo);
                nuevoRegistroA.put("portada" , portada);
                nuevoRegistroA.put("inicio", inicio);
                nuevoRegistroA.put("fin", fin);
                nuevoRegistroA.put("direccion", direccion);
                nuevoRegistroA.put("localidad", localidad);
                nuevoRegistroA.put("cod_postal", cod_postal);
                nuevoRegistroA.put("provincia", provincia);
                nuevoRegistroA.put("longitud", longitud);
                nuevoRegistroA.put("latitud", latitud);
                nuevoRegistroA.put("telefono", telefono);
                nuevoRegistroA.put("email", email);
                nuevoRegistroA.put("web", web);
                nuevoRegistroA.put("facebook", facebook);
                nuevoRegistroA.put("twitter", twitter);
                nuevoRegistroA.put("instagram", instagram);

                MyLog.d(nameAct,  Environment.getExternalStorageState());

                BBDDLocal bdlocal = new BBDDLocal(context, Environment.getExternalStorageDirectory() + File.separator + "eventop.db", null, 2);
                SQLiteDatabase db = bdlocal.getWritableDatabase();


                if(db!=null){
                    try {
                        MyLog.d(nameAct, "Base de datos creada");
                        db.insert("acontecimientos", null, nuevoRegistroA);
                        MyLog.d(nameAct, "Acontecimiento añadido");
                        db.close();
                    }catch(Exception e){
                        MyLog.e(nameAct, "No se ha podido insertar el acontecimiento");
                    }

                }else{MyLog.e(nameAct, "No se ha podido insertar los datos en la base de datos local");}

                if(jsonAcontecimiento.has("eventos")){

                    JSONObject jsonEventos = new JSONObject(jsonAcontecimiento.getString("eventos"));

                    String idE = (jsonEventos.has("id")) ? jsonEventos.getString("id"):"";
                    String idAcont = (jsonEventos.has("id_acontecimiento")) ? jsonEventos.getString("id_acontecimiento"):"";
                    String nombreE = (jsonEventos.has("nombre")) ? jsonEventos.getString("nombre"):"";
                    String descripcionE = (jsonEventos.has("descripcion")) ? jsonEventos.getString("descripcion"):"";
                    String inicioE = (jsonEventos.has("inicio")) ? jsonEventos.getString("inicio"):"";
                    String finE = (jsonEventos.has("fin")) ? jsonEventos.getString("fin"):"";
                    String direccionE = (jsonEventos.has("direccion")) ? jsonEventos.getString("direccion"):"";
                    String localidadE = (jsonEventos.has("localidad")) ? jsonEventos.getString("localidad"):"";
                    String cod_postalE = (jsonEventos.has("cod_postal")) ? jsonEventos.getString("cod_postal"):"";
                    String provinciaE = (jsonEventos.has("provincia")) ? jsonEventos.getString("provincia"):"";
                    String latitudE = (jsonEventos.has("latitud")) ? jsonEventos.getString("latitud"):"";
                    String longitudE = (jsonEventos.has("longitud")) ? jsonEventos.getString("longitud"):"";

                    ContentValues nuevoRegistroE = new ContentValues();

                    nuevoRegistroE.put("id",idE);
                    nuevoRegistroE.put("id_acontecimiento", idAcont);
                    nuevoRegistroE.put("nombre", nombreE);
                    nuevoRegistroE.put("descripcion", descripcionE);
                    nuevoRegistroE.put("inicio", inicioE);
                    nuevoRegistroE.put("fin", finE);
                    nuevoRegistroE.put("direccion", direccionE);
                    nuevoRegistroE.put("localidad", localidadE);
                    nuevoRegistroE.put("cod_postal", cod_postalE);
                    nuevoRegistroE.put("provincia", provinciaE);
                    nuevoRegistroE.put("latitud", latitudE);
                    nuevoRegistroE.put("longitud", longitudE);

                    if(db!=null) {

                        db.insert("eventos", null, nuevoRegistroE);

                    }else{
                        MyLog.e(nameAct, "No se ha podido insertar los datos en la base de datos local");
                    }
                }else{MyLog.d(
                        nameAct, "El acontecimiento no tiene eventos");
                }



            }else{MyLog.e(nameAct, "El archivo json no contiene acontecimientos");


            }

        }catch (Exception e){

            MyLog.e(nameAct, "Error al crear bd local y recuperar datos "+e.getMessage());
        }


        SharedPreferences sharedPreferences = context.getSharedPreferences("Preferencias", context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.commit();

        MyLog.d(nameAct, cadenaAcontecimiento.toString());
        return cadenaAcontecimiento.toString();


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        MyLog.d("AsyncTask", "onPostExecute...");
        progressBar.setVisibility(View.INVISIBLE);


        if(haencontrado) {
            Intent intent = new Intent(context.getApplicationContext(), MostrarAcontecimientoActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();

        }else{
            MyLog.e(nameAct, "No se ha encontrado el acontecimiento");
            Toast.makeText(context, "No existe un acontecimiento con ese id", Toast.LENGTH_SHORT).show();

        }
    }
}
