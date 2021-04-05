package com.example.weatherapp.datamodel.trivia;

public class Category {
    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private int clues_count;


    // Getter Methods 

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getClues_count() {
        return clues_count;
    }

    // Setter Methods 

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setClues_count(int clues_count) {
        this.clues_count = clues_count;
    }
}
