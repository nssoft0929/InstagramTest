package com.nssoft.instagramtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag_account extends Fragment {

    ImageView imageView;
    Button btn;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_account, container, false);

        imageView=view.findViewById(R.id.iv_profile_frag);
        tv=view.findViewById(R.id.tv_bio);
        btn=view.findViewById(R.id.btn_editProfile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ProfileEditActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        SharedPreferences pref=getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        tv.setText(pref.getString("UserName", "사용자 이름을 설정하세요."));

        return view;
    }


}
