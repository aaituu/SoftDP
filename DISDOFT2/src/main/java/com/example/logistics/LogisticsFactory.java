package com.example.logistics;

public abstract class LogisticsFactory {
    public abstract Transport createTransport();
    public abstract Packaging createPackaging();
}