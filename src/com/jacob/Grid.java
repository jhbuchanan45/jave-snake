package com.jacob;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
//import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Grid extends JPanel implements ActionListener {

    private final int SIDE_LENGTH = 25;
    private final int TILE_SIZE = 30;
    private final int PIXEL_SIZE = SIDE_LENGTH * TILE_SIZE;

    private Timer timer;
    private Snake snake;
    private Apple apple;
    private int Score = 0;
    private boolean gameOver;

//    private Image bodyI;
//    private Image headI;
//    private Image appleI;

    public Grid() {

        initGrid();
    }

    private void initGrid() {

        // processes keyboard presses
        addKeyListener(new TAdaptor());

        //ensure game starts with correct parameters
        gameOver = false;
        Score = 0;


        // sets some jFrame properties
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension((PIXEL_SIZE),(PIXEL_SIZE)));
//        loadImages(); // for images as icons
        initGame();
    }

    private void initGame() {

        // init new snake
        snake = new Snake(SIDE_LENGTH);

        // init new apple
        apple = new Apple(SIDE_LENGTH);

        timer = new Timer(200, this);
        timer.start();
    }

    private void move() {
        int Head_x = snake.getHead_x();
        int Head_y = snake.getHead_y();

        //update head position based on each case
        switch(snake.getHead_dir()) {
            case 0: {
                snake.setHead_y(Head_y - 1);
                break;
            }
            case 1: {
                snake.setHead_x(Head_x - 1);
                break;
            }
            case 2: {
                snake.setHead_y(Head_y + 1);
                break;
            }
            case 3: {
                snake.setHead_x(Head_x + 1);
                break;
            }
        }

        //update snake part locations arrays
        snake.updateSnakeParts();

        //check collisions using new head position
        if (checkCollisions()) {
            gameOver = true;
            timer.stop(); //stop timer
            repaint(); //with the gameOver flag set to true this will draw the game over screen

        }
    }

    private boolean checkCollisions() {
        int headX = snake.getHead_x();
        int headY = snake.getHead_y();

        //first we check if inside board
        if ((headX < 0) || (headY < 0) || (headX > SIDE_LENGTH-1) || (headY > SIDE_LENGTH-1)) {
            return true;
        }

        //next we check if inside itself (ie. ran into itself)
        int snakeX[] = snake.getX();
        int snakeY[] = snake.getY();

        for (int i = 1; i < snake.getSnake_length(); i++) {
            if (headX == snakeX[i]) {
                if (headY == snakeY[i]) {
                    return true;
                }
            }
        }

        //finally we check if it hit the apple
        int AppleX = apple.getX();
        int AppleY = apple.getY();

        if (AppleX == headX) {
            if (AppleY == headY) {
                Score += 20;
                apple.Regen(SIDE_LENGTH);
                snake.incSnake_length();
            }
        }

        return false;


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        render(g);
    }

    private void render(Graphics g) {

        if (!gameOver){
            int snakeX[] = snake.getX();
            int snakeY[] = snake.getY();

            //draw snake head
            g.setColor(Color.blue);
            g.fillRect(getPLoc(snake.getHead_x()), getPLoc(snake.getHead_y()), TILE_SIZE, TILE_SIZE);

            //draw each snake body part (ignore head)
            g.setColor(Color.yellow);
            for (int i = 1; i < (snake.getSnake_length()); i++) {
                g.fillRect(getPLoc(snakeX[i]), getPLoc(snakeY[i]), TILE_SIZE, TILE_SIZE);
            }

            //draw apple as red square
            g.setColor(Color.red);
            g.fillRect(getPLoc(apple.getX()), getPLoc(apple.getY()), TILE_SIZE, TILE_SIZE);

            //update score
            updateScore(g);

            Toolkit.getDefaultToolkit().sync();
        }
        else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {

        //parts of the game over text
        String msg = "Game Over!";
        Font endFont = new Font("Calibri", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(endFont);

        //set color and font for drawing
        g.setColor(Color.red);
        g.setFont(endFont);

        //draw message
        g.drawString(msg, (PIXEL_SIZE - metr.stringWidth(msg)) / 2, PIXEL_SIZE / 2);


    }

    private void updateScore(Graphics g) {

        //convert score to string for display and pick styling
        String currentScore = String.valueOf(Score);
        Font scoreFont = new Font("Arial", Font.BOLD, 20);

        //set colour and style
        g.setColor(Color.white);
        g.setFont(scoreFont);

        //draw score
        g.drawString(currentScore, 2,20);
    }

    private int getPLoc(int gridNumber) {
        return (gridNumber * TILE_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //update game state when key pressed
        move();
        repaint();

    }

    private class TAdaptor extends KeyAdapter {

        //modify built in key press processing
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            //restart game if in gameOver state
            if (gameOver) {
                initGrid();
                System.out.println("Restarting!");
            }

            //match key to head direction
            switch (key) {
                case (KeyEvent.VK_UP) -> {
                    if (snake.getHead_dir() != 2) {
                        snake.setHead_dir(0);
                    }
                }
                case (KeyEvent.VK_LEFT) -> {
                    if (snake.getHead_dir() != 3) {
                        snake.setHead_dir(1);
                    }
                }
                case (KeyEvent.VK_DOWN) -> {
                    if (snake.getHead_dir() != 0) {
                        snake.setHead_dir(2);
                    }
                }
                case (KeyEvent.VK_RIGHT) -> {
                    if (snake.getHead_dir() != 1) {
                        snake.setHead_dir(3);
                    }
                }
            }
        }
    }





//    private void loadImages() {
//        ImageIcon iib = new ImageIcon("src/resources/body.png");
//        body = iib.getImage();
//
//
//    }

    // private Snake snake = new Snake(SIDE_LENGTH);

    }

