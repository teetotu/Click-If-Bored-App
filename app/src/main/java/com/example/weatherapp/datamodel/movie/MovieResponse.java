package com.example.weatherapp.datamodel.movie;

import java.util.ArrayList;

public class MovieResponse {
    private float page;
    ArrayList<Movie> results = new ArrayList<>();

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    private float total_pages;
    private float total_results;


    // Getter Methods

    public float getPage() {
        return page;
    }

    public float getTotal_pages() {
        return total_pages;
    }

    public float getTotal_results() {
        return total_results;
    }

    // Setter Methods

    public void setPage(float page) {
        this.page = page;
    }

    public void setTotal_pages(float total_pages) {
        this.total_pages = total_pages;
    }

    public void setTotal_results(float total_results) {
        this.total_results = total_results;
    }
}