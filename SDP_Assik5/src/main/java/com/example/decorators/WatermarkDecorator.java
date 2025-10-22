package com.example.decorators;

import com.example.converteer.FileConverter;

public class WatermarkDecorator extends ConverterDecorator {
    private String watermark;

    public WatermarkDecorator(FileConverter converter, String watermark) {
        super(converter);
        this.watermark = watermark;
    }

    @Override
    public void convert(String inputFile, String outputFormat) {
        super.convert(inputFile, outputFormat);
        System.out.println("[DECORATOR] Added watermark: " + watermark);
    }
}
