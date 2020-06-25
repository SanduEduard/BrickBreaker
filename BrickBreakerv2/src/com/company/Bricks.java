package com.company;

import java.awt.*;

public class Bricks {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public Bricks(int row, int col) {
        map = new int[row][col];
        for (int i=0 ; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++)
                map[i][j] = 1;
        }
        brickWidth = 770/col;
        brickHeight = 240/row;
    }

    public void draw(Graphics2D g) {
        for (int i=0 ; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++)
                if (map[i][j] == 1) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(j*brickWidth+115,i*brickHeight+60, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+115,i*brickHeight+60, brickWidth, brickHeight);
                }

        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
