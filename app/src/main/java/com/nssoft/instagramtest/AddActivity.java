package com.nssoft.instagramtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class AddActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    Adapter_pager adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionChecked=checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionResult=checkSelfPermission(Manifest.permission.CAMERA);
            if (permissionChecked== PackageManager.PERMISSION_DENIED){
                String[] permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
            if (permissionResult== PackageManager.PERMISSION_DENIED){
                String[] permissions=new String[]{Manifest.permission.CAMERA};
                requestPermissions(permissions, 2);
            }
        }

        Toolbar toolbar=findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.cancel_small);

        tabLayout=findViewById(R.id.layout_tab);
        TabLayout.Tab tab=null;

        tab=tabLayout.newTab().setText("갤러리");
        tabLayout.addTab(tab);

        tab=tabLayout.newTab().setText("사진");
        tabLayout.addTab(tab);

        tab=tabLayout.newTab().setText("동영상");
        tabLayout.addTab(tab);

        pager=findViewById(R.id.pager);
        adapter=new Adapter_pager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getSupportActionBar().setTitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }//onCreate

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 2:
                if (grantResults[0]==PackageManager.PERMISSION_DENIED||grantResults[1]==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this, "카메라 권한 승인이 필요합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
