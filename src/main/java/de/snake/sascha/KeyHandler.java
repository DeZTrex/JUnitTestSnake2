package de.snake.sascha;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler extends KeyAdapter {

    SnakeObject object;

    public KeyHandler(SnakeObject object) {
        this.object = object;
    }


    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W && object.getDirection() != Direction.DOWN ) {
            object.setDirection(Direction.UP);
        } else if (code == KeyEvent.VK_S && object.getDirection() != Direction.UP ) {
            object.setDirection(Direction.DOWN);
        } else if (code == KeyEvent.VK_A && object.getDirection() != Direction.RIGHT) {
            object.setDirection(Direction.LEFT);
        } else if (code == KeyEvent.VK_D && object.getDirection() != Direction.LEFT) {
            object.setDirection(Direction.RIGHT);
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }



}
