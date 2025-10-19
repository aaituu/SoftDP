package com.example.core;

/**
 * Handles network streaming operations.
 */
public class NetworkClient {
    private boolean connected;

    public void connect(String url) {
        System.out.println("Connecting to: " + url);
        connected = true;
    }

    public void disconnect() {
        System.out.println("Disconnecting from network");
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }
}
