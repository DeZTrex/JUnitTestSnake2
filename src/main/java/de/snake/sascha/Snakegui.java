package de.snake.sascha;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Snakegui {
    private final SnakeObject snakeObject;
    private int tailCount = 0;
    JFrame frame = new JFrame();

    KeyHandler handler;

    JPanel panel = new JPanel() {
        @Override
        public void paint(Graphics g) {
            snakePaint(g);
        }
    };
    ImageIcon img = new ImageIcon("C:\\Users\\haf\\Downloads\\Apple.png");
    private int blockSize;

    private int xOffSet;
    private int yOffSet;

    private final int snakeAreaWidth;
    private Point apple;


    public Snakegui(SnakeArea snakearea, SnakeObject snakeObject) {
        snakeAreaWidth = snakearea.getWidth();
        this.snakeObject = snakeObject;
        handler = new KeyHandler(this.snakeObject);


    }

    public void loadScreen() {
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Snake - Score: 0");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addKeyListener(handler);
        frame.setVisible(true);
        blockSize = Math.min(panel.getWidth(), panel.getHeight()) / snakeAreaWidth;
        xOffSet = (panel.getWidth() - blockSize * snakeAreaWidth) / 2;
        yOffSet = (panel.getHeight() - blockSize * snakeAreaWidth) / 2;


    }

    private void snakePaint(Graphics g) {
        g.drawImage(img.getImage(), xOffSet + apple.x * blockSize, yOffSet + apple.y * blockSize, blockSize, blockSize, img.getImageObserver());
        paintRaster(g);
        paintSnakeTail(g);
        paintSnakeHead(g);

    }

    private void paintRaster(Graphics g) {

        for (int x = 0; x < snakeAreaWidth; x++) {
            for (int y = 0; y < snakeAreaWidth; y++) {
                g.drawRect(xOffSet + x * blockSize, yOffSet + y * blockSize, blockSize, blockSize);
            }
        }
    }

    public void paint() {
        frame.repaint();
    }

    public void setApple(Point apple) {
        this.apple = apple;
    }

    private void paintSnakeHead(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xOffSet + snakeObject.getPoint().x * blockSize, yOffSet + snakeObject.getPoint().y * blockSize, blockSize, blockSize);

    }

    private synchronized void paintSnakeTail(Graphics g) {
        List<Point> tails = snakeObject.getTails();
        if (!tails.isEmpty()) {
            g.setColor(Color.GREEN);
            for (Point point : tails) {
                g.fillRect(xOffSet + point.x * blockSize, yOffSet + point.y * blockSize, blockSize, blockSize);

            }
        }


    }

    public void updateScore(List<Point> tails) {
        frame.setTitle("Snake - Score: " + tails.size());

    }

}
