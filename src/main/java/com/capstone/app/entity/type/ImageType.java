package com.capstone.app.entity.type;

public enum ImageType {
    PNG("png"),
    WEBP("webp"),
    JPG("jpg"),
    JPEG("jpeg"),;

    private final String type;

    ImageType(String type) {
        this.type = type;
    }
}
