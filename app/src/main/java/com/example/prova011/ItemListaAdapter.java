package com.example.prova011;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ItemListaAdapter extends ArrayAdapter<ItemLista> {
    public ItemListaAdapter(Context context, List<ItemLista> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ItemLista item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        if (item != null) {
            textView.setText(item.getTexto());
            textView.setTextColor(item.getCor());
        }

        return convertView;
    }
}
