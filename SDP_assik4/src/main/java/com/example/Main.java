package com.example;


import com.example.renderer.*;
import com.example.shape.*;

public class Main {
    public static void main(String[] args) {
        Shape vectorCircle = new Circle(new VectorRenderer(), 5.0);
        Shape rasterCircle = new Circle(new RasterRenderer(), 5.0);

        Shape vectorSquare = new Square(new VectorRenderer(), 10.0);
        Shape rasterSquare = new Square(new RasterRenderer(), 10.0);

        vectorCircle.draw();
        rasterCircle.draw();
        vectorSquare.draw();
        rasterSquare.draw();
    }
}
