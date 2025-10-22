package com.example.decorators;

import com.example.converteer.FileConverter;

public abstract class ConverterDecorator implements FileConverter {
    protected FileConverter wrappedConverter;

    public ConverterDecorator(FileConverter converter) {
        this.wrappedConverter = converter;
    }

    @Override
    public void convert(String inputFile, String outputFormat) {
        wrappedConverter.convert(inputFile, outputFormat);
    }
}

