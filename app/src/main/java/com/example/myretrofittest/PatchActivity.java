package com.example.myretrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatchActivity extends AppCompatActivity {

    EditText Roll, Value;
    Spinner Select;
    Button Update;
    TextView Result;
    int old_roll, key=0;
    String value;
    private RestApiInterface restApiInterface;
    //private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch);

        Select = findViewById(R.id.select);
        Roll = findViewById(R.id.roll_id);
        Value = findViewById(R.id.value);
        Update = findViewById(R.id.button);
        Result = findViewById(R.id.result);

        List<String> categories = new ArrayList<String>();
        categories.add("Choose Value");
        categories.add("Roll");
        categories.add("Name");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        Select.setAdapter(dataAdapter);

        restApiInterface = RestClient.getRetrofit().create(RestApiInterface.class);
        Select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String Item = adapterView.getItemAtPosition(i).toString();
                if(Item == "Roll") {
                    Value.setInputType(InputType.TYPE_CLASS_NUMBER);
                    key=1;
                }
                else if(Item == "Name") {
                    Value.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                    key=2;
                }
                else{
                    key=0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                key = 0;
                Toast.makeText(PatchActivity.this, "Choose Value", Toast.LENGTH_SHORT).show();
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(key==1 || key==2) {
                    old_roll = Integer.parseInt(Roll.getText().toString());
                    value = Value.getText().toString();
                    Call<Student> call = restApiInterface.patchData(old_roll, value, key);
                    call.enqueue(new Callback<Student>() {
                        @Override
                        public void onResponse(Call<Student> call, Response<Student> response) {
                            if (!response.isSuccessful()) {
                                Result.setText("Code: " + response.code());
                                return;
                            }
                            Student stud = response.body();
                            String content = "Parameters Send to Server\n\n";
                            content += "Roll: " + stud.getRoll() + "\n";
                            content += "Value: " + stud.getName() + "\n";
                            content += "Key: " + stud.getImage();
                            Result.setText(content);
                        }
                        @Override
                        public void onFailure(Call<Student> call, Throwable t) {
                            Result.setText(t.getMessage());
                        }
                    });
                }
                else{
                    Toast.makeText(PatchActivity.this, "Choose Value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}