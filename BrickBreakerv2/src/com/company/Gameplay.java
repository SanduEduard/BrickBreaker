package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer time;
    private int delay = 5;
    private int playerX = 520;
    private int ballPosX = 220;
    private int ballPosY = 410;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private boolean first = true;

    private Bricks bricks;

    public Gameplay() {
        bricks = new Bricks(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,getWidth(), getHeight());

        //bricks
        bricks.draw((Graphics2D)g);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 900,30);

        //borders
        g.setColor(Color.black);
        g.fillRect(0,0, 5, getHeight());
        g.fillRect(0,0, getWidth(), 5);
        g.fillRect(getWidth()-5,0, 5, getHeight());

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, getHeight()-12, 120, 8);

        //ball
        g.setColor(Color.red);
        g.fillOval(ballPosX-10, ballPosY-10, 20,20);

        //start
        if (!play && first) {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Press ENTER to start", 305,500);
        }
        if (totalBricks == 0) {
            play = false;
            first=false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 100));
            g.drawString("You won!", 280,400);

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Score: "+score, 420,450);

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Press ENTER to restart", 305,500);
        }

        if (ballPosY > 810) {
            play = false;
            first=false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 100));
            g.drawString("GAME OVER", 180,400);

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Score: "+score, 420,450);

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Press ENTER to restart", 305,500);
        }

        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if (play) {
            if (new Rectangle(ballPosX-10, ballPosY-10, 20,20).intersects(new Rectangle(playerX, getHeight()-12, 120, 8))) {
                ballYdir = -ballYdir;
            }

           A: for (int i=0 ; i<bricks.map.length; i++) {
                for (int j = 0; j < bricks.map[0].length; j++)
                    if (bricks.map[i][j] == 1) {
                        int brickX = j*bricks.brickWidth+115;
                        int brickY = i*bricks.brickHeight+60;
                        int brickWidth = bricks.brickWidth;
                        int brickHeight = bricks.brickHeight;
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX-10, ballPosY-10, 20,20);
                        Rectangle brickRect = rect;
                        if (ballRect.intersects(brickRect)) {
                            bricks.setBrickValue(0,i,j);
                            totalBricks--;
                            score += 10;
                            if (ballPosX+19 <= brickRect.x || ballPosX+1 >= brickRect.x + brickRect.width)
                                ballXdir = -ballXdir;
                            else
                                ballYdir = -ballYdir;
                            break A;
                    }
            }
        }

            ballPosX += ballXdir;
            ballPosY += ballYdir;
            if (ballPosX < 0 )
                ballXdir = -ballXdir;
            if (ballPosY < 0)
                ballYdir = -ballYdir;
            if (ballPosX > getWidth()-20)
                ballXdir = -ballXdir;
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= getWidth()-130)
                playerX = getWidth()-130;
            else
                moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 10)
                playerX = 10;
            else
                moveLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play) {
                play = true;
                playerX = 520;
                ballPosX = 220;
                ballPosY = 410;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                totalBricks = 21;
                bricks = new Bricks(3,7);
                repaint();
            } else
                play = true;
        }
    }

    public void moveRight() {
        if (play == true)
        playerX += 20;
    }

    public void moveLeft() {
        if (play == true)
        playerX -= 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    //not used
    }
    @Override
    public void keyTyped(KeyEvent e) {
    //not used
    }
}
