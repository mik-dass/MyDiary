package com.example.joy.projecttest;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Joy on 07/03/2015.
 */
public class DataViewer extends android.support.v4.app.Fragment {
    static ListView listdata;
    DataAdapter data;
    Cursor cursor;
    DataCursorAdapter datacursor;
    int i;

    public DataViewer() {
        // TODO Auto-generated constructor stub
    }

    public void update(){
        cursor=data.fetchdata();
        datacursor.swapCursor(cursor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootview =inflater.inflate(R.layout.data_fragment, container, false);
        listdata=(ListView) rootview.findViewById(R.id.listdata);
        data=new DataAdapter(getActivity());
        cursor=data.fetchdata();
        datacursor=new DataCursorAdapter(getActivity(), cursor);
        listdata.setAdapter(datacursor);
        registerForContextMenu(listdata);
        return rootview;
    }

    class DataCursorAdapter extends CursorAdapter{

        public DataCursorAdapter(Context context, Cursor c) {
            super(context, c,0);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void bindView(View arg0, Context arg1, Cursor arg2) {
            // TODO Auto-generated method stub
            TextView time=(TextView) arg0.findViewById(R.id.texttime);
            TextView descriptiontext=(TextView) arg0.findViewById(R.id.textdescription);
            String timer=arg2.getString(arg2.getColumnIndexOrThrow("date"));
            time.setText(timer);
            String description=arg2.getString(arg2.getColumnIndexOrThrow("description"));
            descriptiontext.setText(description);
            String mood=arg2.getString(arg2.getColumnIndexOrThrow("mood"));
            i=arg2.getInt(0);
            ImageView image=(ImageView) arg0.findViewById(R.id.dataimage);
            switch (mood) {
                case "Excited":
                    image.setImageResource(R.drawable.excited);
                    break;
                case "Happy":
                    image.setImageResource(R.drawable.happy);
                    break;
                case "Lonely":
                    image.setImageResource(R.drawable.lonely);
                    break;
                case "Sad":
                    image.setImageResource(R.drawable.sad);
                    break;
                case "Crazy":
                    image.setImageResource(R.drawable.crazy);
                    break;
                case "Shocked":
                    image.setImageResource(R.drawable.shocked);
                    break;
                case "Cool":
                    image.setImageResource(R.drawable.cool);
                    break;
                default:
                    image.setImageResource(R.drawable.ic_launcher);
                    break;
            }
        }

        @Override
        public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
            // TODO Auto-generated method stub
            return LayoutInflater.from(arg0).inflate(R.layout.data_row, arg2, false);
        }}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Delete");
        i =cursor.getInt(0);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if(item.getTitle()=="Delete"){
            data.delete(i);
            update();
        }
        return super.onContextItemSelected(item);
    }

}
