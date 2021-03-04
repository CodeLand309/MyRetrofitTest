package com.example.myretrofittest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadActivity extends AppCompatActivity {

    private RestApiInterface restApiInterface;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private StudentAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Student> students;

    private int page_number = 1;
    private int item_count = 10;

    //Variables for Pagination
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total=0;
    private int view_threshold= 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        progressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new StudentAdapter(ReadActivity.this,students);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        restApiInterface = RestClient.getRetrofit().create(RestApiInterface.class);

        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponse>> call = restApiInterface.getData(page_number, item_count);
        call.enqueue(new Callback<List<DataResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<DataResponse>> call, @NonNull Response<List<DataResponse>> response) {
                List<Student> students = response.body().get(1).getStudents();
                mAdapter = new StudentAdapter(ReadActivity.this,students);
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(ReadActivity.this, "First Page Loading", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<DataResponse>> call, Throwable t) {
                Toast.makeText(ReadActivity.this, "Cannot Access Server", Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                if(dy>0){
                    if(isLoading){
                        if(totalItemCount>previous_total){
                            isLoading = false;
                            previous_total = totalItemCount;
                        }
                    }
                    if(!isLoading && (totalItemCount-visibleItemCount) <= (pastVisibleItems + view_threshold)){
                        page_number++;
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void performPagination(){
        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponse>> call = restApiInterface.getData(page_number, item_count);
        call.enqueue(new Callback<List<DataResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<DataResponse>> call, @NonNull Response<List<DataResponse>> response) {

                if(response.body().get(0).getStatus().equals("ok")){
                    List<Student> students = response.body().get(1).getStudents();
                    mAdapter.addStudent(students);
                }
                else{
                    //Toast.makeText(ReadActivity.this, "No more Data", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<DataResponse>> call, Throwable t) {
                Toast.makeText(ReadActivity.this, "Cannot Access Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}