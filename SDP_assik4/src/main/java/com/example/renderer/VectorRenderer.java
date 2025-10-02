package com.example.renderer;

public class VectorRenderer implements Renderer {

    @Override
    public void renderShape(String shapeName) {
        System.out.println("Drawing " + shapeName + " as lines and curves (Vector rendering).");
    }
}