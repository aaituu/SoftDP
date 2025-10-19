
package com.example.stream;

/**
 * Core interface for audio streaming operations.
 * Follows Interface Segregation Principle - contains only essential methods.
 */
public interface AudioStream {
    void play();
    void stop();
    byte[] read(int bytes);
    String getMetadata();
}
