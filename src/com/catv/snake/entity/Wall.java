package com.catv.snake.entity;

import com.catv.snake.util.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 墙壁
 */
public class Wall {

    private List<Node> nodes = new ArrayList<>();

    public Wall() {
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
