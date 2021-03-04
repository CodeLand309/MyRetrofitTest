package com.example.myretrofittest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    EditText Name;
    Button Delete;
    TextView Status;
    String name;

    private RestApiInterface restApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Name = findViewById(R.id.name);
        Delete = findViewById(R.id.delete);
        Status = findViewById(R.id.status);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = Name.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("Confirm Delete operation?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        restApiInterface = RestClient.getRetrofit().create(RestApiInterface.class);
                        Call<JsonElement> call = restApiInterface.deleteData(name);
                        call.enqueue(new Callback<JsonElement>() {
                            @Override
                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                if (!response.isSuccessful()) {
                                    Status.setText("Code: " + response);
                                    return;
                                }
                                JsonObject jsonObject = response.body().getAsJsonObject();
                                String content = jsonObject.get("status").getAsString();
                                Status.setText(content);
                            }
                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {
                                Toast.makeText(DeleteActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
}