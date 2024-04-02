package com.capstone.app.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public enum PostStatus {
    PUBLISH("Publish"),
    DRAFT("Draft"),
    TRASH("Trash"),;

    private final String status;

    PostStatus(String status) {
        this.status = status;
    }

    public String getStatusClass() {
        return switch (this) {
            case PUBLISH -> "success";
            case DRAFT -> "secondary";
            case TRASH -> "danger";

        };
    }
}
