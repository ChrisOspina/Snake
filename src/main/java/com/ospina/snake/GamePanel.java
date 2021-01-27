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
        for(int i =0;i<SCREEN_HEIGHT/UNIT_SIZE;i++)
        {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }
        g.setColor(Color.red);
        g.fillOval(applex, appley, UNIT_SIZE, UNIT_SIZE);
        
        for(int i = 0;i<bodyparts;i++)
        {
            if(i == 0){
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            else{
                g.setColor(new Color(45,180,0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
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
        
    }
    
    public void checkCollisions(){
        
    }
    
    public void GameOver(Graphics g){
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            
        }
    }
}
