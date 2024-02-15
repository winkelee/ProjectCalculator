package com.example.inurcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<HistoryResult> {

    public HistoryAdapter(Context context, int resource, List<HistoryResult> history){
        super(context, resource, history);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HistoryResult historyResult = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_cell, parent, false);
        }
        TextView result = convertView.findViewById(R.id.hisResult);
        TextView equasion = convertView.findViewById(R.id.hisEquasion);
        result.setText(historyResult.getResult());
        equasion.setText(historyResult.getEquasion());
        return convertView;
    }
}
