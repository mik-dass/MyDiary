package com.example.joy.projecttest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {
    SharedPreferences sp;
    private static final String mypref="Mypref";
    EditText edtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        sp=getSharedPreferences(mypref, Context.MODE_PRIVATE);
        edtp=(EditText) findViewById(R.id.editl);
    }

    public void login(View v){
        String strp=edtp.getText().toString();
        String spp=sp.getString("pass", " ");
        SharedPreferences.Editor edt=sp.edit();
        if(strp.equals(spp)){
            Intent in=new Intent(LoginActivity.this,MainActivity.class);
            Toast.makeText(getApplicationContext(),"Welcome "+sp.getString("name"," "),Toast.LENGTH_LONG).show();
            edt.putBoolean("visit", true);
            edt.commit();
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_LONG).show();
            edtp.setText("");
        }
    }

}
