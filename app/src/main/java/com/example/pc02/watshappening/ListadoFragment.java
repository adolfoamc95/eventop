package com.example.pc02.watshappening;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class ListadoFragment extends ListFragment {

    private static String nameAct = "ListadoFragment";
    private ArrayList<EventoItem> eventoItems;
    private ListView listView;
    private OnListadoFragmentListener myListener;
    Context context = this.getContext();

    /**
     * El constructor público vacío es obligatorio
     */
    public ListadoFragment() {}

    /**
     * OnCreate no debe crear vistas del fragmento
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.d(nameAct, "onCreate...");

        // Recupera los datos extras que nos envíe la actividad
        if (getArguments() != null) {
            //myParam = getArguments().getString(ARG_PARAM);
        }
    }

    /**
     * OnCreateView crea las vistas del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        MyLog.d(nameAct, "onCreateView...");

        // Carga el layout del fragmento y recupera el ListView para modificarlo posteriormente
        View rootView = inflater.inflate(R.layout.fragment_listado, container, false);
        listView = (ListView) rootView.findViewById(android.R.id.list);
        return rootView;
    }

    /**
     * OnActivityCreated se llama cuando OnCreate de la actividad ha terminado
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyLog.d(nameAct, "onActivityCreated...");

       /* String id;
        final String NOMBRE = "nombre";
        final String DESCRIPCION = "descripcion";
        final String INICIO = "inicio";
        final String FIN = "fin";
        final String DIRECCION = "direccion";
        final String LOCALIDAD = "localidad";
        final String COD_POSTAL = "cod_postal";
        final String PROVINCIA = "provincia";
        final String LATITUD = "latitud";
        final String LONGITUD = "longitud";

        String nombre="", descripcion="", inicio="", fin="", direccion="", localidad="", cod_postal="", provincia="", latitud= "", longitud="";

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", "0");

        BBDDLocal bdlocal = new BBDDLocal(this.getActivity() , Environment.getExternalStorageDirectory() + File.separator + "eventop.db", null, 2);
        SQLiteDatabase db = bdlocal.getReadableDatabase();

        String[] campos = new String[] {NOMBRE, DESCRIPCION, INICIO, FIN, DIRECCION, LOCALIDAD, COD_POSTAL, PROVINCIA, LATITUD, LONGITUD};
        String[] args = new String[]{id};

        Cursor c = db.query("eventos", campos, "id_contecimiento=?", args , null, null, null);

        eventoItems = new ArrayList<EventoItem>();
        if(c.moveToFirst()){

            do {
                nombre = c.getString(c.getColumnIndex(NOMBRE));
                descripcion = c.getString(c.getColumnIndex(DESCRIPCION));
                inicio = c.getString(c.getColumnIndex(INICIO));
                fin = c.getString(c.getColumnIndex(FIN));
                direccion = c.getString(c.getColumnIndex(DIRECCION));
                localidad = c.getString(c.getColumnIndex(LOCALIDAD));
                cod_postal = c.getString(c.getColumnIndex(COD_POSTAL));
                provincia = c.getString(c.getColumnIndex(PROVINCIA));
                latitud = c.getString(c.getColumnIndex(LATITUD));
                longitud = c.getString(c.getColumnIndex(LONGITUD));

                eventoItems.add(new EventoItem(nombre, descripcion, inicio, fin, direccion, localidad, cod_postal, provincia, latitud, longitud));
            }while(c.moveToNext());

        }else{
            MyLog.d(nameAct, "Este acontecimiento no tiene eventos");
        }
        */



        // Crea los elementos a mostrar en la lista
        eventoItems = new ArrayList<EventoItem>();
        eventoItems.add(new EventoItem("1", "Evento numero 1", "Este es el primer evento", "201610191000", "201610191300", "calle", "pueblo", "14530", "provincia", "latitud", "longitud"));
        eventoItems.add(new EventoItem("2", "ABP en Educación Física. Los juegos de siempre: ¿Cómo podemos recuperar en el Colegio los juegos de nuestros mayores?", "Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio", "201610191030", "201610211130", "vega n11", "Montemayor", "14530", "cordoba", "latitud", "longitud"));
        eventoItems.add(new EventoItem("3", "ABP en Educación Física. Los juegos de siempre: ¿Cómo podemos recuperar en el Colegio los juegos de nuestros mayores?", "Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio", "201610191030", "201610211130", "", "", "", "", "latitud", "longitud"));
        eventoItems.add(new EventoItem("4", "ABP en Educación Física. Los juegos de siempre: ¿Cómo podemos recuperar en el Colegio los juegos de nuestros mayores?", "Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio", "201610191030", "201610211130", "", "", "", "", "latitud", "longitud"));

        // Crea el adaptador con los elementos y se lo asigna al ListView
        // Utiliza el layout predefinido de Android para la lista
        EventoAdapter eventosAdapter = new EventoAdapter(getActivity(), android.R.layout.simple_list_item_activated_1, eventoItems);
        listView.setAdapter(eventosAdapter);

        // Si están cargados los fragmentos "listado" y "contenido" al mismo tiempo
        // configura la lista para que sólo se pueda elegir un elemento
        if (getFragmentManager().findFragmentById(R.id.contenido_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MyLog.d(nameAct, "onListItemClick...");

        // Notifica a la actividad el elemento seleccionado
        if (myListener != null) {
            myListener.onListadoInteraction(position, eventoItems.get(position));
        }

        // Marca como activo el elemento seleccionad
        // En este caso no funciona al utilizar un adaptador personalizado
        getListView().setItemChecked(position, true);
    }

    /**
     * Se ejecuta cuando se asocia el fragmento a la actividad
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MyLog.d(nameAct, "onAttach...");

        // Comprueba si está implementado el interfaz en la actividad
        // y se lo asigna
        if (context instanceof OnListadoFragmentListener) {
            myListener = (OnListadoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListadoFragmentListener");
        }
    }

    /**
     * Se ejecuta cuando se desasocia el fragmento de la actividad
     */
    @Override
    public void onDetach() {
        super.onDetach();
        MyLog.d(nameAct, "onDetach...");

        // Elimina el interfaz de la actividad
        myListener = null;
    }

    /**
     * Interfaz que debe definir en su declaración la actividad
     */
    public interface OnListadoFragmentListener {
        void onListadoInteraction(int position, EventoItem item);
    }
}