package com.example;

import com.example.gui.*;
import com.example.logistics.Logistics;
import com.example.logistics.RoadLogistics;
import com.example.logistics.SeaLogistics;

public class Main {
    public static void main(String[] args) {
        // ===== Factory Method =====
        Logistics road = new RoadLogistics();
        road.planDelivery();

        Logistics sea = new SeaLogistics();
        sea.planDelivery();

        System.out.println("-----");

        // ===== Abstract Factory =====
        Application app;

        // Windows UI
        GUIFactory winFactory = new WindowsFactory();
        app = new Application(winFactory);
        app.paint();

        System.out.println("-----");

        // MacOS UI
        GUIFactory macFactory = new MacFactory();
        app = new Application(macFactory);
        app.paint();
    }
}
