package com.example.logistics;

public class RoadPackaging implements Packaging {
    @Override
    public void pack() {
        System.out.println("Using a durable road packaging.");
    }
}