package com.nssoft.instagramtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ProfileEditActivity extends AppCompatActivity {

    TextInputLayout textInputLayout_userName;
    AppCompatEditText et_name, et_userName, et_bio;
    EditText et_email, et_phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        textInputLayout_userName=findViewById(R.id.textInputLayout_Username);
        et_name=findViewById(R.id.et_name);
        et_userName=findViewById(R.id.et_Username);
        et_bio=findViewById(R.id.et_bio);
        et_email=findViewById(R.id.et_email);
        et_phoneNumber=findViewById(R.id.et_phoneNumber);

        Toolbar toolbar=findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.cancel_small);
        actionBar.setTitle("프로필 수정");

        et_userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    textInputLayout_userName.setError("사용자 이름을 입력해야 합니다");
                }else {
                    textInputLayout_userName.setError(null);
                }
            }
        });
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
