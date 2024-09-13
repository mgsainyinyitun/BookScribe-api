package com.sai.bookscribe.constants;

public enum ShelfId {
    SHELF_UPPER(1),
    SHELF_MIDDLE(2),
    SHELF_LOWER(3);

    private final int value;

    ShelfId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}