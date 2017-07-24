package com.catv.snake.util;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏设置
 */
public class Settings {

    /**
     * 面板宽度
     */
    public static int DEFAULT_GRID_WIDTH = 320;
    /**
     * 面板高度
     */
    public static int DEFAULT_GRID_HEIGHT = 320;
    /**
     * 默认移动速度
     */
    public static long DEFAULT_MOVE_INTERVAL = 1000;
    /**
     * 地图尺寸
     */
    public static final int MAP_SIZE = 20;

    /**
     * 单个格子的尺寸
     */
    public static final int SNAKE_SIZE = 16;

    /**
     * 背景图
     */
    public static final Image BACK_IMG = new ImageIcon("pic/background/background.jpg").getImage();
    /**
     * 墙壁
     */
    public static final Image GROUND_IMG = new ImageIcon("pic/game/ground.png").getImage();
    /**
     * 蛇
     */
    public static final Image SNAKE_IMG = new ImageIcon("pic/game/snake.png").getImage();
    /**
     * 蛇
     */
    public static final Image FOOD_IMG = new ImageIcon("pic/game/food.png").getImage();
}
