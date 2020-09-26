package com.jacob;

import java.util.Random;

public class Apple {
    private int x = 0;
    private int y = 0;

    public void Regen(int Side_Length) {
        // create randoms
        Random randomX = new Random();
        Random randomY = new Random();

        //get new x and y for apple
        x = randomX.nextInt(Side_Length);
        y = randomY.nextInt(Side_Length);
    }

    public Apple(int Side_Length) {
        Regen(Side_Length); // get starting apple position
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
