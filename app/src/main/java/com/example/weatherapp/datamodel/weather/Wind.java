package com.example.weatherapp.datamodel.weather;

public class Wind {
    private double speed;
    private double deg;

    public Wind() {
    }

// Getter Methods

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

    // Setter Methods

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
