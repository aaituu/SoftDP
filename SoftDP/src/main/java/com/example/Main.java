package com.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Car car = new CarBuilder()
                .setModel("BMW")
                .setColor("Black")
                .setYear(2024)
                .setEngine(new SportEngine())
                .setHasGPS(true)
                .build();

        System.out.println(car);
    }
}