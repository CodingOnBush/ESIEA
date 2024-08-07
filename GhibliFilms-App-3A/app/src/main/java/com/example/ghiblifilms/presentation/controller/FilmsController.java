package com.example.ghiblifilms.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ghiblifilms.Constants;
import com.example.ghiblifilms.data.GhibliApi;
import com.example.ghiblifilms.presentation.model.ApiFilmsResponse;
import com.example.ghiblifilms.presentation.model.Film;
import com.example.ghiblifilms.presentation.view.FilmsActivity;
import com.example.ghiblifilms.presentation.view.ListAdapter;
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

public class FilmsController {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private FilmsActivity filmsActivity;

    public FilmsController(FilmsActivity filmsActivity, SharedPreferences sharedPreferences, Gson gson) {
        this.filmsActivity = filmsActivity;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void onStart() {
        Slidr.attach(filmsActivity);
        ApiFilmsResponse allFilms = new ApiFilmsResponse();
        ArrayList<Film> arrayList = new ArrayList<Film>();

        arrayList = getDataFromCache();

        if(getDataFromCache() != null){
            filmsActivity.showFilmList(arrayList);
        }else{
            makeApiCall();
        }
    }

    private void makeApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GhibliApi ghibliApi = retrofit.create(GhibliApi.class);

        Call<ArrayList<Film>> call = ghibliApi.getFilm();

        call.enqueue(new Callback<ArrayList<Film>>() {
            @Override
            public void onResponse(Call<ArrayList<Film>> call, Response<ArrayList<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<Film> filmsResponse = response.body();
                    saveList(filmsResponse);
                    filmsActivity.showFilmList(filmsResponse);
                    Toast.makeText(filmsActivity.getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(filmsActivity.getApplicationContext(), "ERROR 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Film>> call, Throwable t) {
                Toast.makeText(filmsActivity.getApplicationContext(), "ERROR :" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveList(ArrayList<Film> filmArrayList) {
        String jsonString = gson.toJson(filmArrayList);

        sharedPreferences
                .edit()
                .putString("jsonFilmList", jsonString)
                .apply();

        Toast.makeText(filmsActivity.getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }

    public ArrayList<Film> getDataFromCache() {
        String jsonFilms = sharedPreferences.getString("jsonFilmList", null);
        //retourne null si il n'y a rien dans jsonFilmList

        if(jsonFilms == null){
            return null;
        }else {
            Type listType = new TypeToken<ArrayList<Film>>(){}.getType();
            // On déserialise la liste
            return gson.fromJson(jsonFilms, listType);
        }
    }
}
