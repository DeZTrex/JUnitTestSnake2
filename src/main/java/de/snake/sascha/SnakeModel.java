package de.snake.sascha;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SnakeModel {

    public static final int STANDARD_GAME_SPEED = 200;
    KeyHandler handler = new KeyHandler(new SnakeObject());
    private final SnakeArea area;
    private final Random random;
    private Point snake;
    private boolean speed = false;
    private Point apple;
    private int score;
    private int gameSpeed = STANDARD_GAME_SPEED;

    private long lastTime;

    public SnakeModel(SnakeArea area, SnakeObject object) {
        random = new Random();
        this.area = area;
        createSnake(object);
        createApple();
    }

    public Point createApple() {
        apple = new Point();
        apple.x = ThreadLocalRandom.current().nextInt(0, area.getHeight());
        apple.y = ThreadLocalRandom.current().nextInt(0, area.getWidth());
        return apple;
    }

    public void createSnake(SnakeObject object) {
        snake = object.getPoint();
        snake.x = random.nextInt(area.getWidth());
        snake.y = random.nextInt(area.getHeight());
    }

    public boolean eatApple() {
        return apple.x == snake.x && apple.y == snake.y;
    }

    public boolean collideSelf(List<Point> tails) {
        for (int i = 1; i < tails.size() - 1; i++) {
            if (snake.x == tails.get(i).x && snake.y == tails.get(i).y) {
                return true;
            }
        }
        return false;
    }

    public int increaseAppleScore() {
        score += 20;
        if(score %SnakeGame.SPEED_SCORE == 0) {
           speed = true;
        }
        return score;
    }

    public boolean collideBorder() {
        return (snake.x < 0 || snake.x >= area.getWidth() || snake.y < 0 || snake.y >= area.getHeight());

    }
    public boolean speedState() {

        return speed;
    }

    public List<Point> resetScore(List<Point> score) {
        score.clear();
        gameSpeed = STANDARD_GAME_SPEED;
        return score;
    }

    public int getScore() {
        return score;
    }


    public void move(SnakeObject object) {
        if (System.currentTimeMillis() - lastTime > gameSpeed) {
            lastTime = System.currentTimeMillis();
            object.move();

        }

    }


        public boolean appleCollide(List<Point> tails) {
            for (int i = 1; i < tails.size() - 1; i++) {
                if (apple.x == tails.get(i).x && apple.y == tails.get(i).y) {
                    apple.x = ThreadLocalRandom.current().nextInt(0, area.getHeight());
                    apple.y = ThreadLocalRandom.current().nextInt(0, area.getWidth());
                }
            }
            return true;

        }
    public Point getApple() {
        return apple;
    }

    public void setGameSpeed() {
        gameSpeed -=50;
        speed = false;
    }
    public void resetTailCounter(int tailSize) {
        tailSize = 0;
    }

}
