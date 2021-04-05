package com.example.weatherapp.datamodel.articles;

import java.util.ArrayList;

public class ArticleResult {
    private String section;
    private String subsection;
    private String title;
    private String abstract_;
    private String url;
    private String uri;
    private String byline;
    private String item_type;
    private String updated_date;
    private String created_date;
    private String published_date;
    private String material_type_facet;
    private String kicker;
    ArrayList<Object> des_facet = new ArrayList<Object>();
    ArrayList<Object> org_facet = new ArrayList<Object>();
    ArrayList<Object> per_facet = new ArrayList<Object>();
    ArrayList<Object> geo_facet = new ArrayList<Object>();
    ArrayList<Object> multimedia = new ArrayList<Object>();
    private String short_url;


    // Getter Methods

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstract() {
        return abstract_;
    }

    public String getUrl() {
        return url;
    }

    public String getUri() {
        return uri;
    }

    public String getByline() {
        return byline;
    }

    public String getItem_type() {
        return item_type;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getMaterial_type_facet() {
        return material_type_facet;
    }

    public String getKicker() {
        return kicker;
    }

    public String getShort_url() {
        return short_url;
    }

    // Setter Methods

    public void setSection(String section) {
        this.section = section;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbstract(String abstract_) {
        this.abstract_ = abstract_;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public void setMaterial_type_facet(String material_type_facet) {
        this.material_type_facet = material_type_facet;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }
}
