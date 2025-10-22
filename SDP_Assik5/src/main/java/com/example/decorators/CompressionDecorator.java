package com.example.decorators;

import com.example.converteer.FileConverter;

public class CompressionDecorator extends ConverterDecorator {
    public CompressionDecorator(FileConverter converter) {
        super(converter);
    }

    @Override
    public void convert(String inputFile, String outputFormat) {
        super.convert(inputFile, outputFormat);
        System.out.println("[DECORATOR] File compressed successfully!");
    }
}
