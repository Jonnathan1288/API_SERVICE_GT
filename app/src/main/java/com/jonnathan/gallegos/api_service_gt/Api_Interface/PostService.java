package com.jonnathan.gallegos.api_service_gt.Api_Interface;

import com.jonnathan.gallegos.api_service_gt.Modelo.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    String API_ROUTE = "/posts";

    @GET(API_ROUTE)
    Call<List<Post>> getPost();

    @GET("/posts/{id}")
    Call<List<Post>> getPostsById(@Path("id") String id);
}
