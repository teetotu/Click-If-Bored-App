package com.example.weatherapp.datamodel.weather;

public class Coord {
    private double lon;
    private double lat;

    public Coord() {
    }
    // Getter Methods

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    // Setter Methods

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return new StringBuilder("[").append(this.lat).append(',').append(this.lon).append(']').toString();
    }
}
