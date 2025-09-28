package com.example;


public class LengthAdapter implements MetricTarget {

    private final ImperialData imperialData;
    private static final double MILES_TO_KM_FACTOR = 1.60934;

    public LengthAdapter(ImperialData imperialData) {
        this.imperialData = imperialData;
    }

    @Override
    public double getDistanceInKilometers() {
        
        double miles = imperialData.getDistanceInMiles();

        return miles * MILES_TO_KM_FACTOR;
    }
}