package com.jacob;

public class Snake {
    private int x[];
    private int y[];
    private final int START_X = 10;
    private final int START_Y = 10;

    // 0 is up, 1 is left, 2 is right, 3 is down
    private int head_dir = 0;

    private int snake_length = 3;
    private int head_x = START_X;
    private int head_y = START_Y;

    public Snake(int SIDE_LENGTH) {
        initSnake(SIDE_LENGTH);
    }

    private void initSnake(int SIDE_LENGTH) {
        // get number of squares on grid
        int grid_number = (SIDE_LENGTH*SIDE_LENGTH);

        // initialise and set starting x snake position
        x = new int [grid_number];
        x[0] = START_X;

        // initialise and set starting y snake position
        y = new int [grid_number];
        y[0] = START_Y;

        for (int i = 1; i < (snake_length); i++) {
            x[i] = START_X;
            y[i] = START_Y+i;
        }
    }

    public int[] getX() {
        return x;
    }

    public int getHead_dir() {
        return head_dir;
    }

    public int getHead_x() {
        return head_x;
    }

    public int getHead_y() {
        return head_y;
    }

    public int getSnake_length() {
        return snake_length;
    }

    public int[] getY() {
        return y;
    }

    public void setHead_dir(int head_dir) {
        this.head_dir = head_dir;
    }

    public void setHead_x(int head_x) {
        this.head_x = head_x;
    }

    public void setHead_y(int head_y) {
        this.head_y = head_y;
    }

    public void incSnake_length() {
        this.snake_length = this.snake_length + 1;
    }

    public void updateSnakeParts() {
        for (int i = (this.snake_length - 1); i > 0; i--) {
            this.x[i] = this.x[(i - 1)];
            this.y[i] = this.y[(i - 1)];
        }
        this.x[0] = this.head_x;
        this.y[0] = this.head_y;
    }
}
