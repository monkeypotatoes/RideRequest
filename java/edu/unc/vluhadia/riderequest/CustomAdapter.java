package edu.unc.vluhadia.riderequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;


class CustomAdapter extends BaseAdapter {
    ArrayList<DataSnapshot> array;
    Context appContext;
    public CustomAdapter(ArrayList<DataSnapshot> dataArray, Context applicationContext) {
        array = dataArray;
        appContext = applicationContext;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return array.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pickup_item, viewGroup, false);

        }



        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView loc = (TextView) convertView.findViewById(R.id.loc);

        DataSnapshot item = (DataSnapshot) getItem(i);
        time.setText(MainActivity.fixTime((String) item.child("time").getValue(String.class)));
        loc.setText(item.child("destination").getValue(String.class));

        return convertView;
    }


}
