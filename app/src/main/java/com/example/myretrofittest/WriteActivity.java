package com.example.myretrofittest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteActivity extends AppCompatActivity {

    private static final int IMG_REQUEST = 777;
    EditText Roll, Name;
    ImageView Image;
    TextView Text;
    Button Add, Select;
    int roll;
    String name;
    String image;
    Bitmap bitmap;
    int flag=0;

    private RestApiInterface restApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Roll = findViewById(R.id.roll);
        Name = findViewById(R.id.name);
        Add = findViewById(R.id.save);
        Select = findViewById(R.id.select);
        Text = findViewById(R.id.text);
        Image = findViewById(R.id.image);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                roll = Integer.parseInt(Roll.getText().toString());
                name = Name.getText().toString();

                if(flag==0)
                    Toast.makeText(WriteActivity.this, "Upload Image", Toast.LENGTH_SHORT).show();
                else {
                    image = imageToString();
                    restApiInterface = RestClient.getRetrofit().create(RestApiInterface.class);

                    Call<Student> call = restApiInterface.postData(roll, name, image);
                    call.enqueue(new Callback<Student>() {
                        @Override
                        public void onResponse(Call<Student> call, Response<Student> response) {
                            if (!response.isSuccessful()) {
                                Text.setText("Code: " + response.code());
                                return;
                            }
                            Student stud = response.body();
                            String content = "";
                            content += "Response Code" + response.code() + "\n";
                            content += "Roll: " + stud.getRoll() + "\n";
                            content += "Name: " + stud.getName() + "\n";
                            String imageString = stud.getImage();

                            byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                            Image.setImageBitmap(decodedImage);

                            Text.setText(content);
                        }

                        @Override
                        public void onFailure(Call<Student> call, Throwable t) {
                            Toast.makeText(WriteActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, IMG_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try{

                //Image.setImageURI(path);
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                //Image.setImageBitmap(bitmap);
                flag=1;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

//                byte[] imageInByte = stream.toByteArray();
//                long lengthbmp = imageInByte.length;

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }
}