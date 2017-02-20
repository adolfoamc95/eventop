package com.example.pc02.watshappening;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContenidoFragment extends Fragment {

    private static String nameAct = "ListadoFragment";
    private String myId = "", myName="", myDescription="", myInicio="", myFin="", mylocalidad="", mycod_postal="", myprovincia="", mydireccion="";

    /**
     * El constructor público vacío es obligatorio
     */
    public ContenidoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MyLog.d(nameAct, "onCreateView...");
        super.onCreateView(inflater, container, savedInstanceState);

        // Si la actividad se recrea (por ejemplo después de una rotación)
        // recupera los datos de estado guardados por "onSaveInstanceState".
        // Necesario cuando los fragmentos "listado" y "contenido" están visibles
        if (savedInstanceState != null) {
            myId = savedInstanceState.getString("id");
            myName= savedInstanceState.getString("nombre");
            myDescription= savedInstanceState.getString("descripcion");
            mydireccion =savedInstanceState.getString("direccion");
            mycod_postal =savedInstanceState.getString("cod_postal");
            myInicio = savedInstanceState.getString("inicio");
            myFin = savedInstanceState.getString("fin");
            mylocalidad = savedInstanceState.getString("localidad");
            myprovincia = savedInstanceState.getString("provincia");

        }

        // Carga el layout del fragmento
        return inflater.inflate(R.layout.fragment_contenido, container, false);
    }

    @Override
    public void onStart() {
        MyLog.d(nameAct, "onStart...");
        super.onStart();

        // Comprueba si se han pasado datos extras al fragmento
        // y se actualiza la vista en función de ellos
        Bundle args = getArguments();
        if (args != null) {
            // Actualiza el contenido en función de los datos extras recibidos
            updateContenidoFragment(args.getString("id"), args.getString("nombre"), args.getString("descripcion"), args.getString("direccion"), args.getString("cod_postal"), args.getString("inicio"), args.getString("fin"), args.getString("localidad"), args.getString("provincia"));
        } else if (!myId.equals("")) {
            // Set article based on saved instance state defined during onCreateView
            // Actualiza el contenido en función de los datos de estado guardados
            updateContenidoFragment(myId, myName, myDescription, myInicio, myFin, mydireccion, mycod_postal, mylocalidad, myprovincia);
        }
    }

    /**
     * Actualiza los datos del fragmento
     */
    public void updateContenidoFragment(String id, String nombre, String descripcion, String inicio, String fin, String direccion, String cod_postal, String localidad, String provincia){
        MyLog.d(nameAct, "updateContenidoFragment...");

        TextView textId = (TextView) getActivity().findViewById(R.id.textViewId);
        textId.setText("Id: "+id);
        TextView textNombre = (TextView) getActivity().findViewById(R.id.textViewNombre);
        textNombre.setText("Nombre: "+nombre);
        TextView textdescrip = (TextView) getActivity().findViewById(R.id.textViewDescripcion);
        textdescrip.setText("Descripcion: "+descripcion);
        TextView textinicio = (TextView) getActivity().findViewById(R.id.textViewInicio);
        textinicio.setText("Inicio: "+inicio);
        TextView textfin = (TextView) getActivity().findViewById(R.id.textViewFin);
        textfin.setText("Fin: "+fin);
        TextView textdireccion = (TextView) getActivity().findViewById(R.id.textViewDireccion);
        textdireccion.setText("Direccion: "+direccion);
        TextView textcod = (TextView) getActivity().findViewById(R.id.textViewCod_postal);
        textcod.setText("Codigo postal: "+cod_postal);
        TextView textlocalidad = (TextView) getActivity().findViewById(R.id.textViewLocalidad);
        textlocalidad.setText("Localidad: "+localidad);
        TextView textprovincia = (TextView) getActivity().findViewById(R.id.textViewProvincia);
        textprovincia.setText("Provincia: "+provincia);
    }

    /**
     * Almacena el estado del fragmento para poder ser recuperado
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        MyLog.d(nameAct, "onSaveInstanceState...");
        super.onSaveInstanceState(outState);

        // Guarda los datos
        outState.putString("id", myId);
        outState.putString("nombre", myName);
        outState.putString("descripcion", myDescription);
        outState.putString("inicio", myInicio);
        outState.putString("fin", myFin);
        outState.putString("direccion", mydireccion);
        outState.putString("cod_postal", mycod_postal);
        outState.putString("localida", mylocalidad);
        outState.putString("provincia", myprovincia);
    }
}