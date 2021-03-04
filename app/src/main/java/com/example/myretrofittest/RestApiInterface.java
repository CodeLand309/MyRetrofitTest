package com.example.myretrofittest;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApiInterface {

    @GET("/get")
    Call<List<DataResponse>> getData(@Query("page_number") int page, @Query("item_count") int items);

    @FormUrlEncoded
    @POST("/post")
    Call<Student> postData(@Field("roll") int roll, @Field("name") String name, @Field("image") String image);

    @FormUrlEncoded
    @PUT("/put")
    Call<Student> putData(@Field("id") int id, @Field("roll") int roll, @Field("name") String name, @Field("image") String image);

    @FormUrlEncoded
    @PATCH("patch")
    Call<Student> patchData(@Field("roll") int roll, @Field("value") String value, @Field("key") int key);

    @DELETE("delete")
    Call<JsonElement> deleteData(@Query("name") String name);

//    @FormUrlEncoded
//    @POST("posts")
//    Call<Post> createPost(@FieldMap Map<String, String> fields);
//    @PUT("posts/{id}")
//    Call<Post> putPost(@Path("id") int id, @Body Post post);
//    @PATCH("posts/{id}")
//    Call<Post> patchPost(@Path("id") int id, @Body Post post);
//    @DELETE("posts/{id}")
//    Call<Void> deletePost(@Path("id") int id);
}
