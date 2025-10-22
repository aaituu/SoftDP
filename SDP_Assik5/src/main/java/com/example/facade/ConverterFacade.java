package com.example.facade;

import com.example.converteer.*;
import com.example.decorators.*;

public class ConverterFacade {
    public void convert(String inputFile, String outputFormat, boolean addWatermark, boolean compress, String watermarkText) {
        FileConverter converter = new BasicFileConverter();

        if (addWatermark) {
            converter = new WatermarkDecorator(converter, watermarkText);
        }

        if (compress) {
            converter = new CompressionDecorator(converter);
        }

        converter.convert(inputFile, outputFormat);
    }
}