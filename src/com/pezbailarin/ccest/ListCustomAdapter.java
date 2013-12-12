package com.pezbailarin.ccest;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListCustomAdapter extends ArrayAdapter<UnitDetails> {
	 
    Context context;
  
    public ListCustomAdapter(Context context, int resourceId,
            List<UnitDetails> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtExtra;
        TextView txtCoste;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        UnitDetails rowItem = getItem(position); 
  
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtExtra = (TextView) convertView.findViewById(R.id.textUExtra);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textUnidad);
            holder.txtCoste = (TextView) convertView.findViewById(R.id.txtCosteList);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iconoUnidad);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getNombre());
        holder.txtExtra.setText(rowItem.getExtra());
        holder.txtCoste.setText(String.valueOf(rowItem.getCoste()));
        holder.imageView.setImageResource(rowItem.getIcon());
 
        return convertView;
    }
}
