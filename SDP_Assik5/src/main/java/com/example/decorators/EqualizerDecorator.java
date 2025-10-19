
package com.example.decorators;

import com.example.stream.AudioStream;

/**
 * Adds equalizer effect to audio stream.
 * Demonstrates dynamic behavior addition.
 */
public class EqualizerDecorator extends AudioDecorator {
    private static final int MIN_BASS = 0;
    private static final int MAX_BASS = 10;
    private static final int MIN_TREBLE = 0;
    private static final int MAX_TREBLE = 10;

    private final int bassLevel;
    private final int trebleLevel;

    public EqualizerDecorator(AudioStream stream, int bass, int treble) {
        super(stream);
        this.bassLevel = validateLevel(bass, MIN_BASS, MAX_BASS, "Bass");
        this.trebleLevel = validateLevel(treble, MIN_TREBLE, MAX_TREBLE, "Treble");
    }

    private int validateLevel(int level, int min, int max, String name) {
        if (level < min || level > max) {
            throw new IllegalArgumentException(
                    String.format("%s level must be between %d and %d", name, min, max)
            );
        }
        return level;
    }

    @Override
    public void play() {
        System.out.println("Applying equalizer (Bass: " + bassLevel +
                ", Treble: " + trebleLevel + ")");
        super.play();
    }

    @Override
    public byte[] read(int bytes) {
        byte[] data = super.read(bytes);
        return applyEqualization(data);
    }

    private byte[] applyEqualization(byte[] data) {
        // Simulated equalization effect
        return data; // In real implementation, would modify audio data
    }

    @Override
    public String getMetadata() {
        return super.getMetadata() +
                String.format(" | EQ(Bass:%d, Treble:%d)", bassLevel, trebleLevel);
    }
}
