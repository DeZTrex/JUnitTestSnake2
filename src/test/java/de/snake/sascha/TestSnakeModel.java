package de.snake.sascha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class TestSnakeModel {

    private KeyHandler handler;
    private SnakeModel sutSnakeModel;
    private SnakeObject snakeObject;

    @BeforeEach
    public void testSetup() {
        SnakeArea snakeArea = new SnakeArea(10, 10);
        snakeObject = new SnakeObject();
        sutSnakeModel = new SnakeModel(snakeArea, snakeObject);
    }

    @Test
    public void testIncreaseAppleScoreShouldReturnFifty() {
        assertEquals(50, sutSnakeModel.increaseAppleScore());
    }

    @Test
    public void testIncreaseAppleScoreTwiceShouldReturnHundred() {
        assertEquals(50, sutSnakeModel.increaseAppleScore());
        assertEquals(100, sutSnakeModel.increaseAppleScore());
    }

    @Test
    public void testEatAppleWithSameCoordinateReturnsTrue() {
        Point apple = sutSnakeModel.createApple();
        sutSnakeModel.createSnake(snakeObject);
        apple.x = snakeObject.getPoint().x;
        apple.y = snakeObject.getPoint().y;
        assertTrue(sutSnakeModel.eatApple());
    }

    @Test
    public void testEatAppleWithDifferentCoordinateReturnsFalse() {
        Point apple = sutSnakeModel.createApple();
        sutSnakeModel.createSnake(snakeObject);
        apple.x = 0;
        snakeObject.getPoint().x = 1;
        assertFalse(sutSnakeModel.eatApple());
    }

    @Test
    public void testSnakeShouldCollideSelf() {
        assertFalse(sutSnakeModel.collideSelf(snakeObject.getTails()));
    }
}

