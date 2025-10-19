package com.example.core;

/**
 * Logging subsystem for tracking playback events.
 */
public class Logger {
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }

    public void logError(String error) {
        System.err.println("[ERROR] " + error);
    }
}