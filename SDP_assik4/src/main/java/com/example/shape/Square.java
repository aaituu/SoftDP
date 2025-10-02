package com.example.shape;

import com.example.renderer.Renderer;

public class Square extends Shape {
    private final double sideLength;

    public Square(Renderer renderer, double sideLength) {
        super(renderer);
        this.sideLength = sideLength;
    }

    @Override
    public void draw() {
        renderer.renderShape("Square with side length " + sideLength);
    }
}