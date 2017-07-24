package com.catv.snake.view;

import com.catv.snake.util.FrameCenter;
import com.catv.snake.util.Settings;

import javax.swing.*;

/**
 * 窗口
 */
public class GameFrame extends JFrame{

    /**
     * 构造器
     */
    public GameFrame(GamePanel gamePanel) {
        //窗口初始化
        init(gamePanel);
    }

    public void init(GamePanel gamePanel) {
        //设置标题
        this.setTitle("贪吃蛇游戏");
        //设置窗口大小
        this.setSize(Settings.DEFAULT_GRID_WIDTH + 5, Settings.DEFAULT_GRID_HEIGHT + 29);
        //设置窗体屏幕居中
        FrameCenter.center(this);
        //设置窗口大小不可改变
        this.setResizable(false);
        //设置默认关闭窗口退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置面板
        this.setContentPane(gamePanel);
        //设置默认打开窗口
        this.setVisible(true);
    }
}
