package com.catv.snake.util;

/**
 * 方向
 */
public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    private final int directionCode;

    public int directionCode() {
        return directionCode;
    }

    Direction(int directionCode) {
        this.directionCode = directionCode;
    }

    public boolean compatibleWith(Direction newDirection) {
        if (Math.abs(this.ordinal() - newDirection.ordinal()) == 2) {
            return false;
        }
        return true;
    }
}
