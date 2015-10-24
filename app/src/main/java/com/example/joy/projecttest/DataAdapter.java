package com.example.joy.projecttest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by Joy on 19/03/2015.
 */
public class DataAdapter {
    DataHelper datahelper;
    public DataAdapter(Context context) {
        // TODO Auto-generated constructor stub
        datahelper=new DataHelper(context);
    }

    public long insert(String description,String mood){
        Date date=new Date();
        ContentValues cv=new ContentValues();
        cv.put(DataHelper.t_description, description);
        cv.put(DataHelper.t_mood, mood);
        cv.put(DataHelper.t_date, date.toLocaleString());
        SQLiteDatabase sq=datahelper.getWritableDatabase();
        long i=sq.insert(DataHelper.t_table, null, cv);
        return i;
    }

    public Cursor fetchdata() {
        // TODO Auto-generated method stub
        SQLiteDatabase sql=datahelper.getWritableDatabase();
        Cursor cursor=sql.rawQuery("SELECT * FROM "+DataHelper.t_table, null);
        return cursor;
    }

    public void delete(int position){
        SQLiteDatabase sql=datahelper.getWritableDatabase();
        sql.execSQL("DELETE FROM "+DataHelper.t_table+" WHERE "+DataHelper.UID+"="+position);
    }

    class DataHelper extends SQLiteOpenHelper{
        private static final String t_name="MyData";
        private static final String t_table="MyTable";
        private static final String UID="_id";
        private static final String t_description="description";
        private static final String t_date="date";
        private static final String t_mood="mood";


        public DataHelper(Context context) {
            super(context, t_name, null, 1);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            // TODO Auto-generated method stub
            String create="CREATE TABLE "+t_table+"("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+t_description+" TEXT,"+t_mood+" TEXT,"+t_date+" TEXT);";
            arg0.execSQL(create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
            String drop="DROP TABLE IF EXISTS "+t_table+";";
            arg0.execSQL(drop);
            onCreate(arg0);
        }

    }

}
