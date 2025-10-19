package com.example.core;

/**
 * Handles audio decoding operations.
 * Single Responsibility: only decodes audio formats.
 */
public class Decoder {
    public void decode(String format) {
        System.out.println("Decoding audio format: " + format);
    }

    public boolean isFormatSupported(String format) {
        return format.equalsIgnoreCase("MP3") ||
                format.equalsIgnoreCase("WAV") ||
                format.equalsIgnoreCase("FLAC");
    }
}