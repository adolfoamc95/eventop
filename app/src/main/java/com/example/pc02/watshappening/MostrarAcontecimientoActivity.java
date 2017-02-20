package com.example.pc02.watshappening;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class MostrarAcontecimientoActivity extends AppCompatActivity {

    private String nameAct = this.getClass().getName();
    LinearLayout linearMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fabMostrarEvento);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MostrarEventoActivity.class);
                startActivity(intent);
            }
        });

        Button button = (Button) findViewById(R.id.buttonMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent2);
            }
        });

        linearMain = (LinearLayout) findViewById(R.id.linearMain);

        String id;
        final String NOMBRE = "nombre";
        final String ORGANIZADOR = "organizador";
        final String DESCRIPCION = "descripcion";
        final String TIPO = "tipo";
        final String PORTADA ="portada";
        final String INICIO = "inicio";
        final String FIN = "fin";
        final String DIRECCION = "direccion";
        final String LOCALIDAD = "localidad";
        final String COD_POSTAL = "cod_postal";
        final String PROVINCIA = "provincia";
        final String TELEFONO =  "telefono" ;
        final String EMAIL = "email";
        final String WEB = "web";
        final String FACEBOOK = "facebook";
        final String TWITTER = "twitter";
        final String INSTAGRAM = "instagram";
        final String LATITUD = "latitud";
        final String LONGITUD = "longitud";
        String nombre = "", organizador = "", descripcion="", tipo="", portada="", inicio="", fin="", direccion="", localidad="", cod_postal="", provincia="", telefono="", email="", web="", facebook="", twitter="", instagram="", latitud="", longitud="";

        SharedPreferences sharedPreferences = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", "0");

        BBDDLocal bdlocal = new BBDDLocal(this.getApplicationContext(), Environment.getExternalStorageDirectory() + File.separator + "eventop.db", null, 2);
        SQLiteDatabase db = bdlocal.getReadableDatabase();

        String[] campos = new String[] {NOMBRE, ORGANIZADOR, DESCRIPCION, TIPO, PORTADA, INICIO, FIN, DIRECCION, LOCALIDAD, COD_POSTAL, PROVINCIA, TELEFONO, EMAIL, WEB, FACEBOOK, TWITTER, INSTAGRAM, LATITUD, LONGITUD};
        String[] args = new String[]{id};

        Cursor c = db.query("acontecimientos", campos, "id=?", args , null, null, null);

        if(c.moveToFirst()){

            nombre = c.getString(c.getColumnIndex(NOMBRE));
            organizador = c.getString(c.getColumnIndex(ORGANIZADOR));
            descripcion = c.getString(c.getColumnIndex(DESCRIPCION));
            tipo = c.getString(c.getColumnIndex(TIPO));
            portada = c.getString(c.getColumnIndex(PORTADA));
            inicio = c.getString(c.getColumnIndex(INICIO));
            fin = c.getString(c.getColumnIndex(FIN));
            direccion = c.getString(c.getColumnIndex(DIRECCION));
            localidad = c.getString(c.getColumnIndex(LOCALIDAD));
            cod_postal = c.getString(c.getColumnIndex(COD_POSTAL));
            provincia = c.getString(c.getColumnIndex(PROVINCIA));
            telefono = c.getString(c.getColumnIndex(TELEFONO));
            email = c.getString(c.getColumnIndex(EMAIL));
            web = c.getString(c.getColumnIndex(WEB));
            facebook = c.getString(c.getColumnIndex(FACEBOOK));
            twitter = c.getString(c.getColumnIndex(TWITTER));
            instagram = c.getString(c.getColumnIndex(INSTAGRAM));
            latitud = c.getString(c.getColumnIndex(LATITUD));
            longitud = c.getString(c.getColumnIndex(LONGITUD));


        }else{
            MyLog.d(nameAct, "No se ha cargado ningun acontecimiento");
        }


        isEmpty(linearMain, nombre, R.drawable.persona, true);
        isEmpty(linearMain, organizador, R.drawable.persona, true);
        isEmpty(linearMain, descripcion, R.drawable.interfaz, true);
        isEmpty(linearMain, tipo, R.drawable.interfaz, true);
        isEmpty(linearMain, portada, R.drawable.interfaz, true);
        isEmpty(linearMain, inicio, R.drawable.reloj, true);
        isEmpty(linearMain, fin, R.drawable.reloj, true);
        isEmpty(linearMain, direccion, R.drawable.tierra, true);
        isEmpty(linearMain, localidad, R.drawable.marcador, true);
        isEmpty(linearMain, cod_postal, R.drawable.marcador, true);
        isEmpty(linearMain, provincia, R.drawable.mapa, true);
        isEmpty(linearMain, telefono, R.drawable.telefono, false);
        isEmpty(linearMain, email, R.drawable.clip, false);
        isEmpty(linearMain, web, R.drawable.web, false);
        isEmpty(linearMain, facebook, R.drawable.simbolo, false);
        isEmpty(linearMain, twitter, R.drawable.twitter, false);
        isEmpty(linearMain, instagram,R.drawable.camara,false);


    }

    private void crearLayoutConContenido(LinearLayout linearMain, String texto, int id, boolean haveText){



        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);



        LinearLayout linearLayout = new LinearLayout(new ContextThemeWrapper(this, R.style.AppTheme));
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //Creamos la imagen
        ImageView imagen = new ImageView(new ContextThemeWrapper(this, R.style.AppTheme));
        imagen.setImageResource(id);

        linearLayout.addView(imagen);

        if(haveText) {

            // Creamos el textview
            TextView textView = new TextView(new ContextThemeWrapper(this, R.style.AppTheme));
            textView.setText(texto);
            textView.setLayoutParams(params);

            linearLayout.addView(textView);
        }



        linearMain.addView(linearLayout);
    }

    private void isEmpty(LinearLayout linearMain, String campo, int idFoto, boolean hasText){

        if(!campo.equals("")){

            crearLayoutConContenido(linearMain, campo,idFoto, hasText);
        }

    }

}
