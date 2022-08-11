package com.jonnathan.gallegos.api_service_gt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jonnathan.gallegos.api_service_gt.Api_Interface.PostService;
import com.jonnathan.gallegos.api_service_gt.Modelo.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listaNoticias;
    private TextView tvsal;
    ArrayList<String> noticias = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaNoticias = findViewById(R.id.listViewMA);
        tvsal = (TextView) findViewById(R.id.textViewMA);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, noticias);
        listaNoticias.setAdapter(arrayAdapter);
        getPost();
    }
    private void getPost() {
        Post post = new Post();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);

        Call<List<Post>> call = postService.getPost();
        //Call<List<Post>> call = postService.getPostsById("3");
        Toast.makeText(MainActivity.this, "allllllllllllll"+call.request(), Toast.LENGTH_LONG).show();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Toast.makeText(MainActivity.this, "allllllllllllll", Toast.LENGTH_SHORT).show();
                if(!response.isSuccessful()){
                    tvsal.setText(response.code());
                    return;
                }
                for(Post post:response.body()){
                    //tvsal.setText(post.getUserId());
                    //Toast.makeText(MainActivity.this, ""+post.getBody(), Toast.LENGTH_SHORT).show();
                    noticias.add(post.getBody());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

}