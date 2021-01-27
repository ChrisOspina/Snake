/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ospina.snake;

import javax.swing.JFrame;

/**
 *
 * @author cospina
 */
public class GameFrame extends JFrame{
    
    GamePanel panel = new GamePanel();
    
    GameFrame(){
        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
}
