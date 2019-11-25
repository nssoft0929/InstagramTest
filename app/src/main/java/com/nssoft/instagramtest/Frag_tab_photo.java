package com.nssoft.instagramtest;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frag_tab_photo extends Fragment {

    ImageView iv_capture, iv_changeCamera;
    CameraView cv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_tab_photo, container, false);
        iv_capture=view.findViewById(R.id.capture_photo);
        iv_changeCamera=view.findViewById(R.id.iv_change_camera);

        iv_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        FileOutputStream fos=null;
                        File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                        if (!path.exists()) path.mkdirs();
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
                        String fileName="IMG_"+sdf.format(new Date())+".jpg";
                        File file=new File(path, fileName);

                        try {
                            fos=new FileOutputStream(file);
                            fos.write(data[0]);
                            fos.flush();
                            fos.close();

                            Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            intent.setData(Uri.fromFile(file));
                            getContext().sendBroadcast(intent);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        return view;
    }


}
