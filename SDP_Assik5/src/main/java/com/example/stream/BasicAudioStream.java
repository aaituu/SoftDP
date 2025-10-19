
package com.example.stream;

/**
 * Basic audio stream implementation without any effects.
 * Single Responsibility: handles basic audio streaming only.
 */
public class BasicAudioStream implements AudioStream {
    private final String trackName;
    private boolean isPlaying;
    private int currentPosition;

    public BasicAudioStream(String trackName) {
        this.trackName = trackName;
        this.isPlaying = false;
        this.currentPosition = 0;
    }

    @Override
    public void play() {
        isPlaying = true;
        System.out.println("Playing: " + trackName);
    }

    @Override
    public void stop() {
        isPlaying = false;
        currentPosition = 0;
        System.out.println("Stopped: " + trackName);
    }

    @Override
    public byte[] read(int bytes) {
        if (!isPlaying) {
            return new byte[0];
        }
        currentPosition += bytes;
        return new byte[bytes]; // Simulated audio data
    }

    @Override
    public String getMetadata() {
        return String.format("Track: %s | Position: %d | Playing: %s",
                trackName, currentPosition, isPlaying);
    }
}
