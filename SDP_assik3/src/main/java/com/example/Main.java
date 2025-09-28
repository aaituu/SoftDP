package com.example;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inputMiles = sc.nextInt();

        ImperialData imperialSource = new ImperialData(inputMiles);

        MetricTarget adapter = new LengthAdapter(imperialSource);
        double distanceInKM = adapter.getDistanceInKilometers();

        System.out.println("\nSource distance (Imperial): " + imperialSource.getDistanceInMiles() + " miles");
        System.out.println("Adapted distance (Metric): " + distanceInKM + " km");

        System.out.println("\n Adapter Pattern successfully demonstrated.");
    }
}