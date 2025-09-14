package com.example;

public class Main {
    public static void main(String[] args) {
        Car car = new CarBuilder()
                .setModel("BMW")
                .setColor("Black")
                .setEngine(new SportEngine())
                .setYear(2024)
                .setHasGPS(true)
                .build();
        Car Elcar = new CarBuilder()
                .setModel("Tesla")
                .setColor("White")
                .setEngine(new ElectricEngine())
                .setYear(2020)
                .setHasGPS(true)
                .build();

        System.out.println(car);
        System.out.println();
        System.out.println(Elcar);
    }
}




