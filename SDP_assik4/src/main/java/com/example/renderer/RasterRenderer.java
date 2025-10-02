package com.example.renderer;

public class RasterRenderer implements Renderer {

    @Override
    public void renderShape(String shapeName) {
        System.out.println("Drawing " + shapeName + " as pixels (Raster rendering).");
    }
}