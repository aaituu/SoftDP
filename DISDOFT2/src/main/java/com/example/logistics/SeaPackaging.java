package com.example.logistics;

public class SeaPackaging implements Packaging {
    @Override
    public void pack() {
        System.out.println("Using a waterproof sea packaging.");
    }
}