package com.nssoft.instagramtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

public class NewPostActivity extends AppCompatActivity {

    ImageView iv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        Toolbar toolbar=findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setTitle("새 게시물");

        et=findViewById(R.id.et_new);
        iv=findViewById(R.id.iv_new);

        Intent intent=getIntent();
        String imgPath=intent.getStringExtra("imgUri");
        Glide.with(this).load(imgPath).into(iv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.share:
                //TODO 사용자 이름, 글내용, 이미지경로 서버로 보내기 + frag_home 으로 이동
                SharedPreferences pref=this.getSharedPreferences("Data", MODE_PRIVATE);
                if (pref.getString("UserName", "").isEmpty()){
                    Toast.makeText(this, "사용자 이름이 있어야 업로드 가능. 프로필을 수정하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }else if (!pref.getString("UserName", "").isEmpty()){
                    String msg=et.getText().toString();
                    String name=pref.getString("UserName", "");
                    Intent intent=getIntent();
                    String imgPath=intent.getStringExtra("imgUri");
                    String serverUrl="http://ynssoft0929.dothome.co.kr/InstagramTest/insertDB.php";
                    SimpleMultiPartRequest smpr=new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            new AlertDialog.Builder(NewPostActivity.this).setMessage("응답"+response).create().show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(NewPostActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                    smpr.addStringParam("name", name);
                    smpr.addStringParam("msg", msg);
                    smpr.addFile("img", imgPath);

                    RequestQueue requestQueue= Volley.newRequestQueue(this);
                    requestQueue.add(smpr);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
