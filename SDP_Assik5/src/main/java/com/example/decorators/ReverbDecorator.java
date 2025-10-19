
package com.example.decorators;

import com.example.stream.AudioStream;

/**
 * Adds reverb effect to audio stream.
 */
public class ReverbDecorator extends AudioDecorator {
    private static final int MIN_ROOM_SIZE = 1;
    private static final int MAX_ROOM_SIZE = 100;
    private static final double MIN_DECAY = 0.0;
    private static final double MAX_DECAY = 1.0;

    private final int roomSize;
    private final double decayFactor;

    public ReverbDecorator(AudioStream stream, int roomSize, double decayFactor) {
        super(stream);
        this.roomSize = validateRoomSize(roomSize);
        this.decayFactor = validateDecay(decayFactor);
    }

    private int validateRoomSize(int size) {
        if (size < MIN_ROOM_SIZE || size > MAX_ROOM_SIZE) {
            throw new IllegalArgumentException(
                    String.format("Room size must be between %d and %d",
                            MIN_ROOM_SIZE, MAX_ROOM_SIZE)
            );
        }
        return size;
    }

    private double validateDecay(double decay) {
        if (decay < MIN_DECAY || decay > MAX_DECAY) {
            throw new IllegalArgumentException(
                    String.format("Decay factor must be between %.1f and %.1f",
                            MIN_DECAY, MAX_DECAY)
            );
        }
        return decay;
    }

    @Override
    public void play() {
        System.out.println("Applying reverb (Room: " + roomSize +
                ", Decay: " + decayFactor + ")");
        super.play();
    }

    @Override
    public byte[] read(int bytes) {
        byte[] data = super.read(bytes);
        return applyReverb(data);
    }

    private byte[] applyReverb(byte[] data) {
        // Simulated reverb effect
        return data;
    }

    @Override
    public String getMetadata() {
        return super.getMetadata() +
                String.format(" | Reverb(Room:%d, Decay:%.2f)", roomSize, decayFactor);
    }
}
