package com.example.ghiblifilms.data;

import com.example.ghiblifilms.presentation.model.Film;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GhibliApi {
    @GET("/films")

    Call<ArrayList<Film>> getFilm();
}
