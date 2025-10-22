package com.example.converteer;

public class BasicFileConverter implements FileConverter {
    @Override
    public void convert(String inputFile, String outputFormat) {
        System.out.println("[INFO] Converting " + inputFile + " to " + outputFormat + "...");
        System.out.println("[SUCCESS] File converted successfully!");
    }
}
