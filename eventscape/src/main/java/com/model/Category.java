package com.model;

public enum Category {
    MUSIC_EVENTS("Music Events"),
    ENTERTAINMENT("Entertainment"),
    FOODS("Foods"),
    SPORTS("Sports");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
