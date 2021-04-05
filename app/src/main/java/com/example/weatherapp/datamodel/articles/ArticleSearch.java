package com.example.weatherapp.datamodel.articles;

import java.util.ArrayList;

public class ArticleSearch {
    private String status;
    private String copyright;
    private String section;
    private String last_updated;
    private float num_results;

    public ArrayList<ArticleResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<ArticleResult> results) {
        this.results = results;
    }

    ArrayList<ArticleResult> results = new ArrayList<ArticleResult>();


    // Getter Methods

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getSection() {
        return section;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public float getNum_results() {
        return num_results;
    }

    // Setter Methods

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public void setNum_results(float num_results) {
        this.num_results = num_results;
    }
}