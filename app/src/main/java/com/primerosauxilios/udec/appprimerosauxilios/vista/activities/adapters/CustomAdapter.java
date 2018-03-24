package com.primerosauxilios.udec.appprimerosauxilios.vista.activities.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.primerosauxilios.udec.appprimerosauxilios.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by daniel on 24/03/18.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> texts;
    private ArrayList<Integer> images;

    public CustomAdapter(Context context, ArrayList<String> texts, ArrayList<Integer> images){
        this.context = context;
        this.texts = texts;
        this.images = images;
    }
    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row;
        row = inflater.inflate(R.layout.fila_listview, parent, false);
        TextView text;
        ImageView image;
        image = (ImageView) row.findViewById(R.id.imgIconCaso);
        text = (TextView) row.findViewById(R.id.tvTexto);
        image.setImageResource(images.get(position));
        text.setText(texts.get(position));

        return row;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
