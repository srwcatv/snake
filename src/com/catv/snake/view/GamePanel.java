package com.catv.snake.view;

import com.catv.snake.control.GameController;
import com.catv.snake.entity.Grid;
import com.catv.snake.entity.Snake;
import com.catv.snake.entity.Wall;
import com.catv.snake.util.Node;
import com.catv.snake.util.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel {

    private final Grid grid;
    
    private int snakeSize = Settings.SNAKE_SIZE;

    public GamePanel(Grid grid) {
        this.grid = grid;
    }


    public void paintComponent(Graphics g) {
        //调用基类方法
        super.paintComponent(g);
        //画窗体
        drawGridBackground(g);
        drawSnake(g, grid.getSnake());
        drawWall(g,grid.getWall());
        drawFood(g, grid.getFood());
        //获取窗口焦点
        this.requestFocusInWindow();
    }

    /**
     * 画墙
     * @param g
     * @param wall
     */
    private void drawWall(Graphics g, Wall wall) {
        List<Node> nodes = wall.getNodes();
        for (Node node : nodes) {
            g.drawImage(
                    Settings.GROUND_IMG,
                    node.getX() * snakeSize,
                    node.getY() * snakeSize,
                    snakeSize,
                    snakeSize,
                    null
            );
        }
    }

    /**
     * 键盘监听
     */
    public void setGameController(GameController GameController) {
        addKeyListener(GameController);
    }

    /**
     * 游戏结束时弹出
     */
    public void showGameOverMessage() {
        JOptionPane.showMessageDialog(null, "游戏结束", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 画蛇
     * @param g
     * @param snake
     */
    public void drawSnake(Graphics g, Snake snake) {
        LinkedList<Node> body = snake.getBody();
        for (Node node : body) {
            g.drawImage(
                    Settings.SNAKE_IMG,
                    node.getX() * snakeSize,
                    node.getY() * snakeSize,
                    snakeSize,
                    snakeSize,
                    null
            );
        }
    }

    /**
     * 画食物
     * @param g
     * @param squareArea
     */
    public void drawFood(Graphics g, Node squareArea) {
        g.drawImage(Settings.FOOD_IMG,squareArea.getX()*snakeSize,squareArea.getY()*snakeSize,null);
    }

    /**
     * 画背景
     * @param g
     */
    public void drawGridBackground(Graphics g) {
        g.drawImage(Settings.BACK_IMG,0,0,Settings.DEFAULT_GRID_WIDTH,Settings.DEFAULT_GRID_HEIGHT,null);
    }
}
