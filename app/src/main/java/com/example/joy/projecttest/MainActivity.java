package com.example.joy.projecttest;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends ActionBarActivity implements Communicator {
    SharedPreferences sp;
    private static final String mypref="Mypref";
    ViewPager vp;
    viewPagerAdapter viewadapter;
    android.support.v7.app.ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=getSharedPreferences(mypref, Context.MODE_PRIVATE);
        registration();
        vp=(ViewPager) findViewById(R.id.pager);
        viewadapter=new viewPagerAdapter(getSupportFragmentManager());
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {}

            @Override
            public void onPageSelected(int i) {
               ab=getSupportActionBar();
               ab.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {}
        });
        vp.setAdapter(viewadapter);
        ab=getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        android.support.v7.app.ActionBar.TabListener tabListener = new android.support.v7.app.ActionBar.TabListener(){
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        };
        ab.addTab(ab.newTab().setText("Diary").setTabListener(tabListener));
        ab.addTab(ab.newTab().setText("Add").setTabListener(tabListener));
        ab.addTab(ab.newTab().setText("Settings").setTabListener(tabListener));
    }

    public void login() {
        // TODO Auto-generated method stub
        boolean b=sp.getBoolean("visit", false);
        if(!b){
            Intent in=new Intent(MainActivity.this,LoginActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        }
    }

    public void registration() {
        // TODO Auto-generated method stub
        boolean b=sp.getBoolean("register", false);
        if(!b){
            Intent in=new Intent(MainActivity.this,RegisterActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        }
        else{
            login();
        }
    }

    public void logout(View v){
        SharedPreferences.Editor edt=sp.edit();
        edt.putBoolean("visit", false);
        edt.commit();
        finish();
    }


    @Override
    public void respond() {
        DataViewer f=(DataViewer)viewadapter.getItem(0);
        f.update();
    }
}
