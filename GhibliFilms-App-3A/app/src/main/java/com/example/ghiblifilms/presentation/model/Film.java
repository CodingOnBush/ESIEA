package com.example.ghiblifilms.presentation.model;

import java.util.ArrayList;

public class Film {
    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;
    private ArrayList<String> people;
    private ArrayList<String> species;
    private ArrayList<String> locations;
    private ArrayList<String> vehicles;
    private String url;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getRt_score() {
        return rt_score;
    }

    public ArrayList<String> getPeople() {
        return people;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public String getUrl() {
        return url;
    }
}
