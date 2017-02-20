package com.example.pc02.watshappening;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AnadirAcontecimientoActivity extends AppCompatActivity {

    String nameAct = "AñadirAcontecimientoActivity";
    Context myContext;
    final private int REQUEST_CODE_INTERNET = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myContext =this;

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        Button buttonAnadir = (Button) findViewById(R.id.buttonAnadir);
        buttonAnadir.setOnClickListener(new View.OnClickListener(){


            public void onClick(View view) {

                String id;
                EditText editTextAnadirID = (EditText) findViewById(R.id.editTextAnadirID);

                // Cojer el id del campo de texto
                id = editTextAnadirID.getText().toString();
                // Comprobar si el campo de texto esta vacio
                if(id.isEmpty()){
                    // Comunicar que el campo de texto esta vacio
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextAnadirID.getWindowToken(), 0);
                    Snackbar.make(view, "Se necesita el campo ID", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {

                    if(isOnline()) {

                        // Comprobar si el usuario tiene permiso de INTERNET
                        if (ContextCompat.checkSelfPermission(myContext, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                            new AddEventAsyncTask(id, myContext, progressBar).execute();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(editTextAnadirID.getWindowToken(), 0);
                        } else {


                            //Solicitar permiso de internet
                            if (Build.VERSION.SDK_INT >= 23) {
                                //Explicar permiso
                                if (ActivityCompat.shouldShowRequestPermissionRationale(AnadirAcontecimientoActivity.this, Manifest.permission.INTERNET)) {
                                    Toast.makeText(myContext, "El permiso de internet es necesario para añadir nuevos acontecimientos", Toast.LENGTH_SHORT).show();
                                }
                                if (ActivityCompat.shouldShowRequestPermissionRationale(AnadirAcontecimientoActivity.this, Manifest.permission.INTERNET)) {
                                    Toast.makeText(myContext, "El permiso de internet es necesario para añadir nuevos acontecimientos", Toast.LENGTH_SHORT).show();
                                    ActivityCompat.requestPermissions(AnadirAcontecimientoActivity.this, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE_INTERNET);
                                }
                            } else {
                                Toast.makeText(myContext, "Necesita garantizar permisos de INTERNET para añadir acontecimientos", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }


            }
        });
    }

    @Override
    protected void onStart() {
        MyLog.d(nameAct, "OnStart...");
        super.onStart();
    }

    @Override
    protected void onResume() {
        MyLog.d(nameAct, "OnResume...");
        super.onResume();
    }

    @Override
    protected void onPause() {
        MyLog.d(nameAct, "OnPause...");
        super.onPause();
    }

    @Override
    protected void onStop() {
        MyLog.d(nameAct, "OnStop...");
        super.onStop();

    }

    @Override
    protected void onRestart() {
        MyLog.d(nameAct, "OnRestart...");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        MyLog.d(nameAct, "OnDestroy...");
        super.onDestroy();
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(myContext, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
