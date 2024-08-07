package com.example.ghiblifilms.presentation.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghiblifilms.R;
import com.example.ghiblifilms.presentation.model.Film;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<Film> list;
    private FilmsActivity filmsActivity;

    ListAdapter(ArrayList<Film> list, FilmsActivity FIlmsActivity) {
        this.list = list;
        this.filmsActivity = FIlmsActivity;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;

        ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.filmPoster);
            txt = v.findViewById(R.id.filmTitle);
        }
    }

    public void add(int position, Film item) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cardview, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Film currentFilm = list.get(position);
        String uri = "@drawable/" + currentFilm.getTitle().toLowerCase().replace(" ", "_").replace("'", "_");
        int imageResource = filmsActivity.getResources().getIdentifier(uri, null, filmsActivity.getPackageName());
        holder.img.setImageResource(imageResource);
        holder.txt.setText((currentFilm.getTitle())+" - "+(currentFilm.getRelease_date()));
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrentFilm(currentFilm);
                filmsActivity.navigateToDetail();
            }
        });

        //TODO holder.txt.setOnClicker(new OnClickListener(){__activer une chek box__})
    }

    private void saveCurrentFilm(Film currentFilm) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        String jsonString = gson.toJson(currentFilm);

        filmsActivity.getSharedPreferences("saveFilms", Context.MODE_PRIVATE)
                .edit()
                .putString("jsonStringCurrentFilm", jsonString)
                .apply();

        Toast.makeText(filmsActivity.getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
