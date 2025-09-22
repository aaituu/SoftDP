package com.example.logistics;

public class RoadLogisticsFactory extends LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new Truck();
    }

    @Override
    public Packaging createPackaging() {
        return new RoadPackaging();
    }
}