/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ospina.snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author cospina
 */
public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyparts = 6;
    int applesEaten;
    int applex;
    int appley;
    char direction = 'E';
    boolean running = false;
    Timer timer;
    Random random;
    
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        
    }
    
    public void startGame(){
        NewApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
        if (running) {
//            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
//                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
//                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//            }
            g.setColor(Color.red);
            g.fillOval(applex, appley, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyparts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten,
                    (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))
                     / 2,g.getFont().getSize());
        }
        else{
            GameOver(g);
        }
    }
    
    public void NewApple(){
        applex = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appley = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    
    public void move(){
        for(int i = bodyparts;i>0;i--)
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction)
        {
            case 'N':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'S':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'W':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'E':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    
    public void checkApple(){
        if((x[0]==applex)&&(y[0]==appley)){
            bodyparts++;
            applesEaten++;
            NewApple();
        }
    }
    
    public void checkCollisions(){
        //checks if head collides with body
        for(int i=bodyparts;i>0;i--)
        {
            if((x[0] == x[i])&&(y[0]==y[i])){
                running = false;
            }
        }
        //checks if head touches left corner
        if(x[0]<0){
            running = false;
        }
        //checks if head touches right corner
        if(x[0]> SCREEN_WIDTH){
            running = false;
        }
        //checks if head touches top border
        if(y[0] <0){
            running = false;
        }
        //checks if head touches bottom border
        if(y[0]>SCREEN_HEIGHT){
            running = false;
        }
        if(!running){
            timer.stop();
        }
    }
    
    public void GameOver(Graphics g){
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten,
                (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))
                / 2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over",(SCREEN_WIDTH-metrics2.stringWidth("Game Over"))
                /2, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction !='E'){
                        direction = 'W';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction !='W'){
                        direction = 'E';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction !='S'){
                        direction = 'N';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction !='N'){
                        direction = 'S';
                    }
                    break;
                    
                    
                
            }
            
        }
    }
}
