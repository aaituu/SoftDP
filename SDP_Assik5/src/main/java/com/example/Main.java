package com.example;

import com.example.facade.MediaFacade;
import com.example.core.*;
import com.example.stream.*;
import com.example.decorators.*;

/**
 * Demonstrates both Facade and Decorator patterns.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== MEDIA PLAYER DEMONSTRATION ===\n");

        // Initialize subsystems (normally would use DI framework)
        Decoder decoder = new Decoder();
        BufferManager bufferManager = new BufferManager();
        NetworkClient networkClient = new NetworkClient();
        Logger logger = new Logger();

        // Create facade - simple API for complex operations
        MediaFacade player = new MediaFacade(decoder, bufferManager,
                networkClient, logger);

        // Scenario 1: Simple playback
        System.out.println("\n--- Scenario 1: Simple Playback ---");
        player.playTrack("Song of the Sea", "MP3");
        player.setVolume(75);
        System.out.println(player.getStreamInfo());
        player.stop();

        // Scenario 2: Playback with effects
        System.out.println("\n--- Scenario 2: Decorated Playback ---");
        player.playTrackWithEffects("Mountain Echo", "FLAC",
                true, true, false);
        System.out.println(player.getStreamInfo());
        player.stop();

        // Scenario 3: Preset application
        System.out.println("\n--- Scenario 3: Preset Application ---");
        player.applyPreset("rock", "Thunder Road");
        System.out.println(player.getStreamInfo());
        player.stop();

        // Scenario 4: Network streaming
        System.out.println("\n--- Scenario 4: Network Streaming ---");
        player.streamFromUrl("https://stream.example.com/live", "Live Radio");
        player.stop();

        // Scenario 5: Manual decorator composition
        System.out.println("\n--- Scenario 5: Manual Decoration ---");
        demonstrateManualDecoration();

        System.out.println("\n=== DEMONSTRATION COMPLETE ===");
    }

    /**
     * Shows direct decorator usage without facade.
     */
    private static void demonstrateManualDecoration() {
        AudioStream stream = new BasicAudioStream("Custom Track");

        // Chain decorators
        stream = new NormalizationDecorator(stream, 0.9);
        stream = new EqualizerDecorator(stream, 6, 6);
        stream = new ReverbDecorator(stream, 40, 0.5);

        stream.play();
        System.out.println(stream.getMetadata());
        stream.stop();
    }
}