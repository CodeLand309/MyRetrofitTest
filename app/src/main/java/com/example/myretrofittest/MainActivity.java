package com.example.myretrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Read, Write, Update, Patch, Delete;

    //public RestApiInterface restApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Read = findViewById(R.id.read);
        Write = findViewById(R.id.write);
        Update = findViewById(R.id.update);
        Patch = findViewById(R.id.patch);
        Delete = findViewById(R.id.delete);

//        //OkHttpClient.Builder client = new OkHttpClient.Builder();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.29.202:5000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                //             .client(client.build())
//                .build();
//
//        restApiInterface = retrofit.create(RestApiInterface.class);

        Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, ReadActivity.class);
            startActivity(i);
//                Call<List<Student>> call = restApiInterface.getData();
//                call.enqueue(new Callback<List<Student>>() {
//                    @Override
//                    public void onResponse(Call<List<Student>> call, DataResponse<List<Student>> response) {
//                        if (!response.isSuccessful()) {
//                            Text.setText("Code: " + response.code());
//                            return;
//                        }
//                        List<Student> students = response.body();
//                        for (Student stud : students) {
//                            String content = "";
//                            content += "Roll: " + stud.getRoll() + "\n";
//                            content += "Name: " + stud.getName() + "\n\n";
//                            Text.append(content);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Student>> call, Throwable t) {
//                    Toast.makeText(MainActivity.this, "Cannot Access Server", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

        Write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, WriteActivity.class);
                startActivity(i);
//                finish();
//                List<Student> student= new ArrayList<Student>();
//                student.add(new Student(3, "Mithun"));
//                student.add(new Student(4, "Arif"));
//                Call<List<Student>> call = restApiInterface.postData(student);
//                call.enqueue(new Callback<List<Student>>() {
//                    @Override
//                    public void onResponse(Call<List<Student>> call, DataResponse<List<Student>> response) {
//                        if (!response.isSuccessful()) {
//                            Text.setText("Code: " + response.code());
//                            return;
//                        }
//                        List<Student> stud = response.body();
//
//                        for (Student student : stud) {
//                            String content = "";
//                            content += "Roll: " + student.getRoll() + "\n";
//                            content += "Name: " + student.getName() + "\n\n";
//                            Text.append(content);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Student>> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, "Cannot Access Server", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PutActivity.class);
                startActivity(i);
            }
        });

        Patch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PatchActivity.class);
                startActivity(i);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(i);
            }
        });
    }
}