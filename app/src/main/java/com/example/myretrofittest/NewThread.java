package com.example.myretrofittest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class NewThread implements Runnable {

    Bitmap image;
    String value;
    public String getImageString(){
        return value;
    }
    public NewThread(Bitmap image){
        this.image = image;
    }
    @Override
    public void run() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        value = Base64.encodeToString(imgByte, Base64.DEFAULT);
    }
}
