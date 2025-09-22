package com.example.logistics;

public class SeaLogisticsFactory extends LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new Ship();
    }

    @Override
    public Packaging createPackaging() {
        return new SeaPackaging();
    }
}