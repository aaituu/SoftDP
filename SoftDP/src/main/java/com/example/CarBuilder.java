package com.example;

public class CarBuilder {
    String model;
    String color;
    Engine engine;
    int year;
    boolean hasGPS;


    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }
    public CarBuilder setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }
    public CarBuilder setYear(int year) {
        this.year = year;
        return this;
    }
    public CarBuilder setHasGPS(boolean hasGPS) {
        this.hasGPS = hasGPS;
        return this;
    }

    public Car build() {
        return new Car(this);
    }
}


