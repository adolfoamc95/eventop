package com.example.pc02.watshappening;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adolf on 16/02/2017.
 */

public class EventoAdapter extends ArrayAdapter<EventoItem> {

    private static final String nameAct = "EventoAdapter";
    private final Context context;
    private final List<EventoItem> values;

    public EventoAdapter(Context context, int textViewResourceId, List<EventoItem> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyLog.d(nameAct, "getView...");
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_evento, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.evento_nombre);
        textView.setText(values.get(position).getNombre());
        TextView textView1 = (TextView) rowView.findViewById(R.id.evento_inicio);
        textView1.setText(values.get(position).getInicio());

        return rowView;
    }
}
