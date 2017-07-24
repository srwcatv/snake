package com.catv.snake.util;

/**
 * 节点类
 */
public class Node {
    private final int x;
    private final int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println(111);
        return obj instanceof Node?this.x == ((Node)obj).getX() && this.y == ((Node)obj).getY():super.equals(obj);
    }
}
