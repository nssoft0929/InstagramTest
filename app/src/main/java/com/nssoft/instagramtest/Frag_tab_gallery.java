package com.nssoft.instagramtest;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;

import static android.app.Activity.RESULT_OK;

public class Frag_tab_gallery extends Fragment {

    ImageView imageView;
    Button btn_choose, btn_write;
    final int GET_GALLERY_IMAGE=1;
    String imgPath;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_tab_gallery, container, false);

        imageView=view.findViewById(R.id.iv_gallery);
        btn_choose =view.findViewById(R.id.btn_gallery);
        btn_write=view.findViewById(R.id.btn_write);

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), NewPostActivity.class);
                startActivity(intent);
                intent.putExtra("imgUri", imgPath);

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==GET_GALLERY_IMAGE&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            Uri imageUri=data.getData();
            imageView.setImageURI(imageUri);
            imgPath=getRealPathFromUri(imageUri);


            getActivity().invalidateOptionsMenu();
            btn_write.setEnabled(true);


        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.toolbar_frag_gallery, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.next:
                startActivity(new Intent(getContext(), NewPostActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    String getRealPathFromUri(Uri uri){
        String[] proj={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getContext(), uri, proj, null, null, null);
        Cursor cursor=loader.loadInBackground();
        int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(column_index);
        cursor.close();
        return result;
    }
}
