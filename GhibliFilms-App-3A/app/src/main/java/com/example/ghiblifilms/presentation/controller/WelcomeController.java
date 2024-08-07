package com.example.ghiblifilms.presentation.controller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ghiblifilms.R;
import com.example.ghiblifilms.presentation.view.FilmsActivity;
import com.example.ghiblifilms.presentation.view.WelcomeActivity;

public class WelcomeController {
    WelcomeActivity welcomeActivity;

    public WelcomeController(WelcomeActivity welcomeActivity) {
        this.welcomeActivity = welcomeActivity;
    }

    public void onStart(){
        Button button_welcome = (Button) welcomeActivity.findViewById(R.id.welcome_button);
        button_welcome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onButtonClick();
            }
        });
    }

    public void onButtonClick(){
        if(!isNetworkAvailable()){
            Toast.makeText(welcomeActivity.getApplicationContext(), "You need a first connection to get API text", Toast.LENGTH_LONG).show();
        }else{
            navigateToRecyclerviewFilms();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) welcomeActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void navigateToRecyclerviewFilms(){
        Intent intent = new Intent(welcomeActivity.getApplicationContext(), FilmsActivity.class);
        welcomeActivity.startActivity(intent);
    }
}
