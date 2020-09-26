package com.jacob;

import java.awt.EventQueue;
import javax.swing.JFrame;

    public class Game extends JFrame {

        public Game() {

            initUI();
        }

        private void initUI() {

            //add the main game logic section
            add(new Grid());

            // set some JFrame options
            setResizable(false);
            pack();

            setTitle("Java Snake");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
        }

        public static void main(String[] args) {

            EventQueue.invokeLater(() -> {
                JFrame ex = new Game();
                ex.setVisible(true);
            });
        }
}
