package com.nssoft.instagramtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    FragmentManager fm;
    FragmentTransaction ft;
    Frag_home frag_home;
    Frag_search frag_search;
    Frag_add frag_add;
    Frag_activity frag_activity;
    Frag_account frag_account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionChecked=checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionChecked== PackageManager.PERMISSION_DENIED){
                String[] permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
        }

        bnv=findViewById(R.id.bnv);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_camera_alt_black_24dp);


        frag_home=new Frag_home();
        frag_search=new Frag_search();
        frag_add=new Frag_add();
        frag_activity=new Frag_activity();
        frag_account=new Frag_account();

        setFrag(0);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bnv_home:
                        setFrag(0);
                        break;
                    case R.id.bnv_search:
                        setFrag(1);
                        break;
                    case R.id.bnv_add:
                        setFrag(2);
                        break;
                    case R.id.bnv_favorite:
                        setFrag(3);
                        break;
                    case R.id.bnv_account:
                        setFrag(4);
                        break;

                }

                return true;
            }
        });


    }//onCreate

    void setFrag(int n){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();

        switch (n){
            case 0:
                ft.replace(R.id.frame, frag_home);
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frame, frag_search);
                ft.addToBackStack(null);
                ft.commit();
                break;
                case 2:
                ft.replace(R.id.frame, frag_add);
                ft.addToBackStack(null);
                ft.commit();
                break;
                case 3:
                ft.replace(R.id.frame, frag_activity);
                ft.addToBackStack(null);
                ft.commit();
                break;
                case 4:
                ft.replace(R.id.frame, frag_account);
                ft.addToBackStack(null);
                ft.commit();
                break;

        }
    }//setFrag

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                //TODO CameraActivity
                break;
            }
            case R.id.send:
                //TODO DirectActivity
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
