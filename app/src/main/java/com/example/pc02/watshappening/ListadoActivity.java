package com.example.pc02.watshappening;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class ListadoActivity extends AppCompatActivity {

    String nameAct = "ListadoActivity";
    private ArrayList<AcontecimientoItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnadirAcontecimientoActivity.class);
                startActivity(intent);
            }
        });

        // Crear elementos

        items = new ArrayList<AcontecimientoItem>();

        BBDDLocal bdlocal = new BBDDLocal(this, Environment.getExternalStorageDirectory() + File.separator + "eventop.db", null, 2);
        SQLiteDatabase db = bdlocal.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM acontecimientos", null);

        if(c.moveToFirst()){
            do{
                String id = c.getString(0);
                String nombre = c.getString(1);

                items.add(new AcontecimientoItem(id, nombre));

            }while(c.moveToNext());

        }else{
            Toast toast = Toast.makeText(ListadoActivity.this, "No hay acontecimientos en la memoria local, descargate algunos", Toast.LENGTH_SHORT);
            toast.show();
        }
/*
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercer acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "decimo acontecimiento"));
        items.add(new AcontecimientoItem("11", "undecimo acontecimiento"));
        items.add(new AcontecimientoItem("12", "dueodecimo acontecimiento"));
        items.add(new AcontecimientoItem("13", "trigesimo acontecimiento"));
        items.add(new AcontecimientoItem("14", "cuatrigesimo acontecimiento"));
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercer acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "decimo acontecimiento"));
        items.add(new AcontecimientoItem("11", "undecimo acontecimiento"));
        items.add(new AcontecimientoItem("12", "dueodecimo acontecimiento"));
        items.add(new AcontecimientoItem("13", "trigesimo acontecimiento"));
        items.add(new AcontecimientoItem("14", "cuatrigesimo acontecimiento"));
*/

        // Se inicializa el RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Se crea el Adaptador con los datos
        AcontecimientoAdapter adaptador = new AcontecimientoAdapter(items);

        // Se asocia el elemento con una acción al pulsar el elemento
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento
                MyLog.d(nameAct, "Click en RecyclerView");
                int position = recyclerView.getChildAdapterPosition(v);
                SharedPreferences sharedPreferences = getSharedPreferences("Preferencias", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id", items.get(position).getId());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MostrarAcontecimientoActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(ListadoActivity.this, "Posision: "+String.valueOf(position) + " ID: "+items.get(position).getId() + " Nombre: " + items.get(position).getNombre(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // Se asocia el Adaptador al RecyclerView
        recyclerView.setAdapter(adaptador);

        // Se muestra el RecyclerView en vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_Aboutus:
                Intent intent = new Intent(getApplicationContext(), AcercaDeActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
