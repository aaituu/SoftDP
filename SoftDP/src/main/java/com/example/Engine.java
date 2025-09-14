package com.example;

public class Engine {
    private final String Type;
    private final int HorsePower;


    public Engine(String Type, int HorsePower) {
        this.Type = Type;
        this.HorsePower = HorsePower;
    }

    @Override
    public String toString() {
        return Type+" "+HorsePower+"hp";
    }
}
