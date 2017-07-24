package com.catv.snake.entity;

import com.catv.snake.util.Direction;
import com.catv.snake.util.Node;
import com.catv.snake.util.Settings;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 蛇的地图
 */
public class Grid {

    /**
     * 地图
     */
    public boolean[][] gameMap;
    /**
     * 宽度
     */
    private int width;
    /**
     * 高度
     */
    private int height;

    /**
     * 蛇
     */
    private Snake snake;
    /**
     * 食物
     */
    private Node food;
    /**
     * 墙
     */
    private Wall wall;
    /**
     * 随机数对象
     */
    private Random random;

    /**
     * 默认方向
     */
    private Direction snakeDirection = Direction.LEFT;

    /**
     * 初始化地图
     *
     * @param width  宽度
     * @param height 高度
     */
    public Grid(int width, int height) {
        //地图宽度
        this.width = width;
        //地图高度
        this.height = height;
        //初始化地图
        gameMap = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            Arrays.fill(gameMap[i], false);
        }
        //初始化随机数对象
        random = new Random();
        //初始化一条蛇
        initSnake();
        //初始化墙
        initWall();
        //初始化一个食物
        createFood();
    }

    /**
     * 创建食物
     *
     * @return 返回食物
     */
    private Node createFood() {
        //是否覆盖
        boolean isOver = true;
        while (isOver) {
            // 使用Random设置x和y
            int x = random.nextInt(Settings.MAP_SIZE - 1);
            int y = random.nextInt(Settings.MAP_SIZE - 1);
            //判断地图中的位置是否已经被占用
            if (!gameMap[x][y]) {
                food = new Node(x, y);
                isOver = false;
            }
        }
        return food;
    }

    /**
     * 初始化一条蛇
     *
     * @return 返回一条蛇
     */
    private Snake initSnake() {
        //初始化蛇类
        snake = new Snake();
        // 设置Snake的Body
        LinkedList<Node> body = snake.getBody();
        body.add(new Node(Settings.MAP_SIZE >> 1, Settings.MAP_SIZE >> 1));
        // 更新地图
        updateStatus(body);
        return snake;
    }

    /**
     * 初始化墙
     */
    private void initWall() {
        //创建墙对象
        wall = new Wall();
        List<Node> nodes = wall.getNodes();
        for (int x = 0; x < Settings.MAP_SIZE; x++) {
            for (int y = 0; y < Settings.MAP_SIZE; y++) {
                if (x == 0 || x == Settings.MAP_SIZE - 1) {
                    nodes.add(new Node(x, y));
                }
            }
        }
        // 更新地图
        updateStatus(nodes);
    }

    /**
     * 下一个方向
     *
     * @return
     */
    public boolean nextRound() {
        //按当前方向移动贪吃蛇
        snake.move(snakeDirection);
        //吃到自己和墙游戏结束
        if (this.snake.isEatBody()) {
            return true;
        }
        if (this.isEatWall()) {
            return true;
        }

        //头部原来是食物
        if (isEatFood()) {
            //把原来move操作时删除的尾部添加回来
            this.snake.eat(food);
            //创建一个新的食物
            createFood();
        }
        //更新棋盘状态并返回游戏是否结束的标志
        this.updateStatus(this.snake.getBody());
        return false;
    }

    /**
     * 是否吃到墙
     *
     * @return
     */
    private boolean isEatWall() {
        List<Node> nodes = this.wall.getNodes();
        for (Node node : nodes) {
            if (node.equals(this.snake.getHead())){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否吃到食物
     *
     * @return
     */
    public boolean isEatFood() {
        if (this.food.getX() == this.snake.getHead().getX() && this.food.getY() == this.snake.getHead().getY()) {
            return true;
        }
        return false;
    }

    /**
     * 改变方向
     *
     * @param newDirection 新的方向
     */
    public void changeDirection(Direction newDirection) {
        if (snakeDirection.compatibleWith(newDirection)) {
            snakeDirection = newDirection;
        }
    }

    /**
     * 更新地图
     *
     * @param nodes
     */
    public void updateStatus(List<Node> nodes) {
        // 更新地图
        for (int x = 0; x < Settings.MAP_SIZE; x++) {
            for (int y = 0; y < Settings.MAP_SIZE; y++) {
                for (Node node : nodes) {
                    if (x == node.getX() && y == node.getY()) {
                        gameMap[x][y] = true;
                    }
                }
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Node getFood() {
        return food;
    }

    public Wall getWall() {
        return wall;
    }
}
