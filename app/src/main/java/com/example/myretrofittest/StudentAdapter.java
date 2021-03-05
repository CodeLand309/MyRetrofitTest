package com.example.myretrofittest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> mStudentList;
    private Context mContext;
    String url="http://192.168.29.202:5000/image/";
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mImageView;
        public StudentViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.roll);
            mTextView2 = itemView.findViewById(R.id.name);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
    public StudentAdapter(Context context, List<Student> studentList) {
        mStudentList = studentList;
        mContext = context;
//        StudentListFull = new ArrayList<>(StudentList);
    }
//    public void setStudentList(List<Student> studentList) {
//        mStudentList = studentList;
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.student, parent, false);
        return new StudentViewHolder(v);
    }
    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student currentItem = mStudentList.get(position);
        holder.mTextView1.setText(Integer.toString(currentItem.getRoll()));
        holder.mTextView2.setText(currentItem.getName());
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                byte[] imageBytes = Base64.decode(currentItem.getImage(), Base64.DEFAULT);
//                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                decodedImage.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
//                holder.mImageView.post(new Runnable(){
//                    @Override
//                    public void run(){
//                        holder.mImageView.setImageBitmap(decodedImage);
//                    }
//                });
//            }
//        };
//        Thread mythread = new Thread(runnable);
//        mythread.start();

        url += currentItem.getName();
        Picasso.get().load(url).fit().centerInside().into(holder.mImageView);
//        Glide.with(mContext).load(url).into(holder.mImageView);
        url = "http://192.168.29.202:5000/image/";
    }
    @Override
    public int getItemCount() {
        if(mStudentList!=null)
            return mStudentList.size();
        return 0;
    }

    public void addStudent(List<Student> students){
        for(Student stud : students){
            mStudentList.add(stud);
        }
        notifyDataSetChanged();
    }
}
