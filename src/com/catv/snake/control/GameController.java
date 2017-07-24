package com.catv.snake.control;

import com.catv.snake.entity.Grid;
import com.catv.snake.util.Direction;
import com.catv.snake.util.Settings;
import com.catv.snake.view.GameFrame;
import com.catv.snake.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 游戏控制器
 */
public class GameController extends KeyAdapter implements Runnable {

    private Grid grid;

    private boolean running;

    private final GamePanel gamePanel;

    public GameController() {
        this.running = true;
        // 画出棋盘和贪吃蛇
        this.grid = new Grid(Settings.MAP_SIZE, Settings.MAP_SIZE);
        // 基于Grid初始化gamaView
        this.gamePanel = new GamePanel(grid);
        //设置游戏监听器
        gamePanel.addKeyListener(this);
        //创建窗口
        new GameFrame(gamePanel);
        new Thread(this).start();
    }

    public void keyPressed(KeyEvent e) {
        // 处理按键
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            grid.changeDirection(Direction.UP);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            grid.changeDirection(Direction.DOWN);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            grid.changeDirection(Direction.LEFT);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            grid.changeDirection(Direction.RIGHT);
        }
        //刷新面板
        gamePanel.repaint();
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
            // 进入游戏下一步
            boolean isOver = grid.nextRound();
            // 如果结束，则退出游戏
            if (isOver){
                running = false;
                this.gamePanel.showGameOverMessage();
            }
            // 如果继续，则绘制新的游戏页面
            gamePanel.repaint();
        }
    }
}
