package com.example.weatherapp.datamodel.trivia;

public class TriviaQa {
    private int id;
    private String answer;
    private String question;
    private int value;
    private String airdate;
    private String created_at;
    private String updated_at;
    private int category_id;
    private String game_id;
    private String invalid_count;
    Category category;


    // Getter Methods

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getValue() {
        return value;
    }

    public String getAirdate() {
        return airdate;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getGame_id() {
        return game_id;
    }

    public String getInvalid_count() {
        return invalid_count;
    }

    public Category getCategory() {
        return category;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public void setInvalid_count(String invalid_count) {
        this.invalid_count = invalid_count;
    }

    public void setCategory(Category categoryCategory) {
        this.category = categoryCategory;
    }
}

