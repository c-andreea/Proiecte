package org.example;

import ecran.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    public static void main(String[] args) {


        JFrame frame=new JFrame("calculator");
        frame.setContentPane(new Screen(500,700));
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}