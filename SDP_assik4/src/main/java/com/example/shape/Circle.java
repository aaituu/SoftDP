package com.example.shape;

import com.example.renderer.Renderer;

public class Circle extends Shape {
    private final double radius;

    public Circle(Renderer renderer, double radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.renderShape("Circle with radius " + radius);
    }
}