
package com.example.decorators;

import com.example.stream.AudioStream;

/**
 * Normalizes audio volume levels.
 */
public class NormalizationDecorator extends AudioDecorator {
    private static final double MIN_TARGET = 0.1;
    private static final double MAX_TARGET = 1.0;

    private final double targetLevel;

    public NormalizationDecorator(AudioStream stream, double targetLevel) {
        super(stream);
        this.targetLevel = validateTarget(targetLevel);
    }

    private double validateTarget(double target) {
        if (target < MIN_TARGET || target > MAX_TARGET) {
            throw new IllegalArgumentException(
                    String.format("Target level must be between %.1f and %.1f",
                            MIN_TARGET, MAX_TARGET)
            );
        }
        return target;
    }

    @Override
    public void play() {
        System.out.println("Applying normalization (Target: " + targetLevel + ")");
        super.play();
    }

    @Override
    public byte[] read(int bytes) {
        byte[] data = super.read(bytes);
        return normalize(data);
    }

    private byte[] normalize(byte[] data) {
        // Simulated normalization
        return data;
    }

    @Override
    public String getMetadata() {
        return super.getMetadata() +
                String.format(" | Normalized(%.2f)", targetLevel);
    }
}