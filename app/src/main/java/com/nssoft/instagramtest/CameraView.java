package com.nssoft.instagramtest;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder holder;
    Camera camera;
    Context context;

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera=Camera.open(0);
        Camera.Parameters params=camera.getParameters();
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        camera.setParameters(params);

        try {
            camera.setPreviewDisplay(holder);
            camera.setDisplayOrientation(90);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera=null;
    }

    public void takepicture(){
        camera.takePicture(shutterCallback, rawCallback, imgCallback);
    }

    Camera.ShutterCallback shutterCallback=new Camera.ShutterCallback() {
        @Override
        public void onShutter() {

        }
    };

    Camera.PictureCallback rawCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    };

    Camera.PictureCallback imgCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            new SaveImg().execute(data);
        }
    };

    private class SaveImg extends AsyncTask<byte[], Void, Void>{

        @Override
        protected Void doInBackground(byte[]... data) {
            FileOutputStream fos=null;
            File path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!path.exists())path.mkdirs();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
            String fileName="Img_"+sdf.format(new Date())+"jpg";
            File outputFile=new File(path, fileName);
            try {
                fos=new FileOutputStream(outputFile);
                fos.write(data[0]);
                fos.flush();
                fos.close();

                Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intent.setData(Uri.fromFile(outputFile));
                context.sendBroadcast(intent);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
