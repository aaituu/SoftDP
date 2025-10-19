
package com.example.decorators;

import com.mediaplayer.stream.AudioStream;

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

package com.mediaplayer.decorators;

import com.mediaplayer.stream.AudioStream;

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

// ============================================
// SUBSYSTEMS (Hidden behind Facade)
// ============================================

package com.mediaplayer.core;

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

package com.mediaplayer.core;

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

package com.mediaplayer.core;

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

package com.mediaplayer.core;

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

// ============================================
// FACADE PATTERN IMPLEMENTATION
// ============================================

package com.mediaplayer.facade;

import com.mediaplayer.core.*;
        import com.mediaplayer.stream.AudioStream;
import com.mediaplayer.stream.BasicAudioStream;
import com.mediaplayer.decorators.*;

/**
 * Facade that simplifies complex media player operations.
 * Dependency Inversion: depends on AudioStream abstraction.
 * Hides complexity of subsystems from client.
 */
public class MediaFacade {
    private final Decoder decoder;
    private final BufferManager bufferManager;
    private final NetworkClient networkClient;
    private final Logger logger;

    private AudioStream currentStream;
    private int volume;
    private static final int DEFAULT_VOLUME = 50;
    private static final int MIN_VOLUME = 0;
    private static final int MAX_VOLUME = 100;

    // Dependency Injection through constructor
    public MediaFacade(Decoder decoder, BufferManager bufferManager,
                       NetworkClient networkClient, Logger logger) {
        this.decoder = decoder;
        this.bufferManager = bufferManager;
        this.networkClient = networkClient;
        this.logger = logger;
        this.volume = DEFAULT_VOLUME;
    }

    /**
     * Simplified API to play a track with automatic subsystem coordination.
     */
    public void playTrack(String trackName, String format) {
        logger.log("Initiating playback: " + trackName);

        if (!decoder.isFormatSupported(format)) {
            logger.logError("Unsupported format: " + format);
            return;
        }

        decoder.decode(format);
        bufferManager.fillBuffer();

        currentStream = new BasicAudioStream(trackName);
        currentStream.play();

        logger.log("Playback started successfully");
    }

    /**
     * Play track with effects applied via decorators.
     */
    public void playTrackWithEffects(String trackName, String format,
                                     boolean useEqualizer, boolean useReverb,
                                     boolean useNormalization) {
        logger.log("Preparing playback with effects: " + trackName);

        if (!decoder.isFormatSupported(format)) {
            logger.logError("Unsupported format: " + format);
            return;
        }

        decoder.decode(format);
        bufferManager.fillBuffer();

        // Build decorated stream
        AudioStream stream = new BasicAudioStream(trackName);

        if (useEqualizer) {
            stream = new EqualizerDecorator(stream, 7, 5);
        }
        if (useReverb) {
            stream = new ReverbDecorator(stream, 50, 0.6);
        }
        if (useNormalization) {
            stream = new NormalizationDecorator(stream, 0.8);
        }

        currentStream = stream;
        currentStream.play();

        logger.log("Playback with effects started");
    }

    /**
     * Stream from network URL.
     */
    public void streamFromUrl(String url, String trackName) {
        logger.log("Starting network stream: " + url);

        networkClient.connect(url);
        if (!networkClient.isConnected()) {
            logger.logError("Failed to connect to: " + url);
            return;
        }

        bufferManager.fillBuffer();
        currentStream = new BasicAudioStream(trackName);
        currentStream.play();

        logger.log("Network streaming started");
    }

    /**
     * Stop current playback and cleanup resources.
     */
    public void stop() {
        if (currentStream != null) {
            currentStream.stop();
            logger.log("Playback stopped");
        }

        bufferManager.clearBuffer();

        if (networkClient.isConnected()) {
            networkClient.disconnect();
        }
    }

    /**
     * Set volume level with validation.
     */
    public void setVolume(int newVolume) {
        if (newVolume < MIN_VOLUME || newVolume > MAX_VOLUME) {
            logger.logError("Volume must be between " + MIN_VOLUME +
                    " and " + MAX_VOLUME);
            return;
        }

        volume = newVolume;
        logger.log("Volume set to: " + volume);
    }

    /**
     * Apply preset configuration.
     */
    public void applyPreset(String presetName, String trackName) {
        logger.log("Applying preset: " + presetName);

        AudioStream stream = new BasicAudioStream(trackName);

        switch (presetName.toLowerCase()) {
            case "rock":
                stream = new EqualizerDecorator(stream, 8, 7);
                stream = new ReverbDecorator(stream, 30, 0.4);
                break;
            case "jazz":
                stream = new EqualizerDecorator(stream, 5, 6);
                stream = new ReverbDecorator(stream, 60, 0.7);
                break;
            case "classical":
                stream = new EqualizerDecorator(stream, 4, 8);
                stream = new NormalizationDecorator(stream, 0.7);
                break;
            default:
                logger.logError("Unknown preset: " + presetName);
                return;
        }

        currentStream = stream;
        decoder.decode("MP3");
        bufferManager.fillBuffer();
        currentStream.play();

        logger.log("Preset applied successfully");
    }

    /**
     * Get current stream information.
     */
    public String getStreamInfo() {
        if (currentStream == null) {
            return "No active stream";
        }
        return currentStream.getMetadata();
    }
}


package com.mediaplayer;

import com.mediaplayer.facade.MediaFacade;
import com.mediaplayer.core.*;
        import com.mediaplayer.stream.*;
        import com.mediaplayer.decorators.*;

/**
 * Demonstrates both Facade and Decorator patterns.
 */
public class MediaPlayerDemo {

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