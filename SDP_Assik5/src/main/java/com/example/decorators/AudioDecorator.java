
package com.example.decorators;

import com.example.stream.AudioStream;

/**
 * Abstract decorator base class.
 * Open/Closed Principle: open for extension, closed for modification.
 * Liskov Substitution: can replace AudioStream anywhere.
 */
public abstract class AudioDecorator implements AudioStream {
    protected final AudioStream wrappedStream;

    protected AudioDecorator(AudioStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Stream cannot be null");
        }
        this.wrappedStream = stream;
    }

    @Override
    public void play() {
        wrappedStream.play();
    }

    @Override
    public void stop() {
        wrappedStream.stop();
    }

    @Override
    public byte[] read(int bytes) {
        return wrappedStream.read(bytes);
    }

    @Override
    public String getMetadata() {
        return wrappedStream.getMetadata();
    }
}
