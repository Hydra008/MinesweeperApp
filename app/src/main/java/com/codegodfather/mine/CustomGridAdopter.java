package com.codegodfather.mine;

/**
 * Created by godfather on 2017-10-17.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by godfather on 2017-09-29.
 */

public class CustomGridAdopter extends BaseAdapter {

    private Context context;
    private String[] item;
    LayoutInflater inflater;


    public CustomGridAdopter(Context context,String[] item) {
        this.context=context;
        this.item=item;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return item.length ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view=inflater.inflate(R.layout.cell,null);
        }
        TextView tv = view.findViewById(R.id.textView);



        if(i<81) {
            if(item[i].equalsIgnoreCase("*"))
            {
                tv.setTextColor(Color.RED);
                tv.setText(item[i]);
            }
            else  if(item[i].equalsIgnoreCase("F"))
            {
                tv.setTextColor(Color.BLUE);
                tv.setText(item[i]);
            }
            else
            {
                tv.setTextColor(Color.DKGRAY);
                tv.setText(item[i]);
            }

        }
        return view;
    }
}
