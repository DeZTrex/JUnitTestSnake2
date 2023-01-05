package de.snake.sascha;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SnakeObject {

    private final Point point;

    private Point apple;

    public SnakeModel model;

    private Direction direction;

    private List<Point> tails;
    private boolean tailAdded;
    private boolean blocked;

    public SnakeObject() {
        point = new Point();
        direction = Direction.LEFT;
        tails = new ArrayList<>();
        tailAdded = false;

    }

    public Point getPoint() {
        return point;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if(!blocked) {
            if(!Direction.getOppositeDirection(direction).equals(this.direction)) {
                this.direction = direction;
            }
            blocked = true;

        }

    }


    public void move() {
        blocked = false;
        if(!tailAdded){
            if(!tails.isEmpty()) {
                Point lastTail = tails.remove(tails.size()-1);
                lastTail.x = point.x;
                lastTail.y = point.y;
                tails.add(0, lastTail);
            }
        }
        tailAdded = false;

        switch (direction) {
            case UP -> point.y--;
            case DOWN -> point.y++;
            case LEFT -> point.x--;
            case RIGHT -> point.x++;

        }

    }

    public void addTail() {
        tails.add(0,new Point(point.x, point.y));
        tailAdded = true;
        int tailscount = tails.size();
        System.out.println(tailscount);
    }

    public List<Point> getTails() {
        return tails;
    }
    public void clearTails() {
        tails.clear();
    }

}

