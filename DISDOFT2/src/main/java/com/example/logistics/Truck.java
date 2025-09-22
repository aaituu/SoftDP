package com.example.logistics;

public class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("delivery on Truck");
    }
}
