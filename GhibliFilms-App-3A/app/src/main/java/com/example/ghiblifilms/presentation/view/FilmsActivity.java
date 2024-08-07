package com.example.ghiblifilms.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghiblifilms.Constants;
import com.example.ghiblifilms.R;
import com.example.ghiblifilms.data.GhibliApi;
import com.example.ghiblifilms.presentation.controller.FilmsController;
import com.example.ghiblifilms.presentation.model.ApiFilmsResponse;
import com.example.ghiblifilms.presentation.model.Film;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.r0adkll.slidr.Slidr;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FilmsActivity extends Activity {
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FilmsController filmsController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recyclerview_films);

        filmsController = new FilmsController(
                this,
                getSharedPreferences("saveFilms", Context.MODE_PRIVATE),
                new GsonBuilder()
                        .setLenient()
                        .create());
        filmsController.onStart();
    }


    public void showFilmList(ArrayList<Film> filmArrayList) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);// Pour augmenter un petit peu les perf
        layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(layoutManager);


        listAdapter = new ListAdapter(filmArrayList, this);
        recyclerView.setAdapter(listAdapter);

    }


    public void navigateToDetail() {
        Intent intent = new Intent(this, DetailActivity.class);
        this.startActivity(intent);
    }

}
