package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setBounds(600,100,1000,800);
        window.setTitle("Brick Breaker");
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Gameplay gamePlay = new Gameplay();
        window.add(gamePlay);


    }
}
