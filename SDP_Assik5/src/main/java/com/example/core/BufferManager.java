package com.example.core;

/**
 * Manages audio buffering for smooth playback.
 */
public class BufferManager {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private final int bufferSize;

    public BufferManager() {
        this.bufferSize = DEFAULT_BUFFER_SIZE;
    }

    public BufferManager(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void fillBuffer() {
        System.out.println("Buffering " + bufferSize + " bytes...");
    }

    public void clearBuffer() {
        System.out.println("Clearing buffer");
    }
}