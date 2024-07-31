package com.music_service.music_service_demo.exceptions;

public class TrackNotFoundException extends RuntimeException {

    public TrackNotFoundException() {}

    public TrackNotFoundException(String message) {
        super(message);
    }

    public TrackNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrackNotFoundException(Throwable cause) {
        super(cause);
    }

    public TrackNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
