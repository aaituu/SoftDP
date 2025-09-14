package com.example;

public class Car {
    private String model;
    private String color;
    private Engine engine;
    private int year;
    private boolean hasGPS;

    Car(CarBuilder builder) {
        this.model = builder.model;
        this.color = builder.color;
        this.engine = builder.engine;
        this.year = builder.year;
        this.hasGPS = builder.hasGPS;
    }

    @Override
    public String toString() {
        return "Model = " + model +
                "\nColor = " + color +
                "\nengine = " + engine +
                "\nYear = " + year +
                "\nhasGPS = " + hasGPS;
    }

}



