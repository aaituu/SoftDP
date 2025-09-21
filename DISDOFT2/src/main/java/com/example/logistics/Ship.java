package com.example.logistics;

public class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Доставка по морю на корабле.");
    }
}
