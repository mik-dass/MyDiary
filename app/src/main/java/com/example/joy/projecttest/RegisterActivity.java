package com.example.joy.projecttest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends ActionBarActivity {
    SharedPreferences sp;
    private static final String mypref="Mypref";
    EditText edtn;
    EditText edtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);
        edtn=(EditText) findViewById(R.id.editText1);
        edtp=(EditText) findViewById(R.id.editText2);
        sp=getSharedPreferences(mypref, Context.MODE_PRIVATE);
    }

    public void register(View v){
        String name=edtn.getText().toString();
        String pass=edtp.getText().toString();
        if(name.trim().equals("")  || pass.trim().equals("")){
            Toast.makeText(getApplication(),"Please Wite a name and password",Toast.LENGTH_LONG).show();
        }
        else{
            SharedPreferences.Editor edt = sp.edit();
            edt.putString("name", edtn.getText().toString());
            edt.putString("pass", edtp.getText().toString());
            edt.putBoolean("register", true);
            edt.putBoolean("visit", true);
            edt.commit();
            Intent in = new Intent(RegisterActivity.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        }
    }


}
