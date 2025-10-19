
package com.example.facade;

import com.example.core.*;
import com.example.stream.AudioStream;
import com.example.stream.BasicAudioStream;
import com.example.decorators.*;

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
