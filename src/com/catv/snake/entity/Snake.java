package com.catv.snake.entity;

import com.catv.snake.util.Direction;
import com.catv.snake.util.Node;
import com.catv.snake.util.Settings;

import java.util.LinkedList;

/**
 * 蛇类
 */
public class Snake {

    /**
     * 蛇身
     */
    private LinkedList<Node> body = new LinkedList<>();
    /**
     * 蛇尾
     */
    private Node lastNode;

    /**
     * 蛇吃到食物
     *
     * @param food
     * @return
     */
    public Node eat(Node food) {
        this.addTail(this.lastNode);
        return food;
    }

    /**
     * 移动
     *
     * @param direction
     * @return
     */
    public Node move(Direction direction) {
        //保存蛇尾
        lastNode = body.getLast();
        //取出蛇头
        Node head = getHead();
        //得到蛇头的坐标
        int y = head.getY();
        int x = head.getX();
        //更新蛇头坐标
        if (direction == Direction.UP) {
            y--;
            if (y == -1) {
                y = Settings.MAP_SIZE - 1;
            }
        } else if (direction == Direction.DOWN) {
            y++;
            if (y == Settings.MAP_SIZE) {
                y = 0;
            }
        } else if (direction == Direction.LEFT) {
            x--;
            if (x == -1) {
                x = Settings.MAP_SIZE - 1;
            }
        } else if (direction == Direction.RIGHT) {
            x++;
            if (x == Settings.MAP_SIZE) {
                x = 0;
            }
        }
        //删除蛇尾
        body.removeLast();
        //添加新的蛇头
        body.addFirst(new Node(x, y));
        // 返回移动之前的尾部Node
        return lastNode;
    }

    /**
     * 判断是否吃到自己
     * @return
     */
    public boolean isEatBody() {
        for (int i = 4;i < body.size();i++){
            if (body.get(i).getY() == getHead().getY() && body.get(i).getX() == getHead().getX()){
                return true;
            }
        }
        return false;
    }

    public Node getHead() {
        return body.getFirst();
    }

    public Node addTail(Node area) {
        this.body.addLast(area);
        return area;
    }

    public LinkedList<Node> getBody() {
        return body;
    }
}
