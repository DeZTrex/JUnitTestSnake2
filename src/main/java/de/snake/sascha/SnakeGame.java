package de.snake.sascha;

public class SnakeGame {

    public static final int SPEED_SCORE = 1000;

    public SnakeGame(String[] args) {
         
        SnakeArea snakearea = new SnakeArea(14, 14);
        SnakeObject snakeObject = new SnakeObject();
        KeyHandler handler = new KeyHandler(snakeObject);
        SnakeModel model = new SnakeModel(snakearea, snakeObject);
        Snakegui gui = new Snakegui(snakearea, snakeObject);
        gui.loadScreen();
        while(true) {
            model.move(snakeObject);
            gui.setApple(model.getApple());
            gui.paint();
            if(model.eatApple()) {
                model.createApple();
                snakeObject.addTail();
                gui.updateScore(snakeObject.getTails());
                model.appleCollide(snakeObject.getTails());
            }
            else if(model.collideSelf(snakeObject.getTails())) {
                snakeObject.clearTails();
                gui.updateScore(model.resetScore(snakeObject.getTails()));

            }
            else if(model.collideBorder()) {
                snakeObject.clearTails();
                model.createSnake(snakeObject);
                gui.updateScore(model.resetScore(snakeObject.getTails()));
            }
            if(model.getScore() % SPEED_SCORE == 0 && model.getScore() != 0 && model.speedState()) {
                model.setGameSpeed();

            }
        }



    }
}