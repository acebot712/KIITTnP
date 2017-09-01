package com.example.asterdan712.kiittnp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by asterdan712 on 9/1/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<CustomObject> objects;

    private class ViewHolder{
        TextView textView;
        TextView textView2;
    }

    public CustomAdapter(@NonNull Context context, ArrayList<CustomObject> objects) {
        layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.custom_row, null);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(objects.get(position).getNoticeURL());
        holder.textView2.setText(objects.get(position).getNoticeDesc());
        return convertView;

    }
}
