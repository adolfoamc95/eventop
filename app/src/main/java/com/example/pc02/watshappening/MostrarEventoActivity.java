package com.example.pc02.watshappening;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MostrarEventoActivity  extends AppCompatActivity
        implements ListadoFragment.OnListadoFragmentListener {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.d(TAG, "onCreate...");
        setContentView(R.layout.activity_mostra_evento);

        // Comprueba qué layout se ha cargado:
        // - Si es "unique_fragment" sólo hay un fragmento en el layout
        //   y lo tenemos que añadir nosotros al "FrameLayout".
        // - Si no, los dos fragmentos conviven en la actividad
        //   y se añaden automáticamente desde el layout al "fragment".
        if (findViewById(R.id.unique_fragment) != null) {

            // Sólo crea el fragmento "listado" si no se ha restaurado
            // de un estado previo de la actividad
            // para evitar que se solapen múltiples instancias
            if (savedInstanceState == null) {
                ListadoFragment myListadoFragment = new ListadoFragment();

                // Recupera los datos extras que posea la actividad
                // y se los pasa al fragmento
                myListadoFragment.setArguments(getIntent().getExtras());

                // Añade el fragmento "listado" al "FrameLayout"
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.unique_fragment, myListadoFragment)
                        .commit();
            }
        }
    }

    /**
     * Implementa el método al que llama la interfaz del fragmento "listado".
     * Se ejecuta cada vez que se hace click en un elemento de la lista.
     */
    public void onListadoInteraction(int position, EventoItem item){
        MyLog.d(TAG, "onListadoInteraction...");

        // Captura el fragmento "contenido" para ver si esta cargado en la actividad
        // - Si existe, actualizamos sus datos, porque tendremos los dos fragmentos cargados.
        // - Si no existe, lo creamos y lo cargamos en el "FrameLayout" sustituyendo la lista.
        ContenidoFragment myContenidoFragment = (ContenidoFragment)
                getSupportFragmentManager().findFragmentById(R.id.contenido_fragment);

        if (myContenidoFragment != null) {
            myContenidoFragment.updateContenidoFragment(item.getId(), item.getNombre(), item.getDescripcion(), item.getInicio(), item.getFin(), item.getDireccion(), item.getCod_postal(), item.getLocalidad(), item.getProvincia());
        } else {
            // Crea el fragmento y le pasa datos extras
            ContenidoFragment newContenidoFragment = new ContenidoFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("id", item.getId());
            args.putString("nombre", item.getNombre());
            args.putString("descripcion", item.getDescripcion());
            args.putString("inicio", item.getInicio());
            args.putString("fin", item.getFin());
            args.putString(("direccion"), item.getDireccion());
            args.putString("cod_postal", item.getCod_postal());
            args.putString("localidad", item.getLocalidad());
            args.putString("provincia", item.getProvincia());
            newContenidoFragment.setArguments(args);

            // Reemplaza el fragmento "listado" por el fragmento "contenido"
            // Añade la transacción a la pila para poder volver atrás
            // Al volver atrás se detiene el fragmento pero no se destruye
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.unique_fragment, newContenidoFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}