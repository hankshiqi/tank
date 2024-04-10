package com.hank.tank;

import javax.swing.*;
import java.awt.*;

public class test01 extends JFrame {
    mypan pan=null;
    public static void main(String[] args) {
        new test01();
    }
    test01(){
        pan=new mypan();
        this.add(pan);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
class mypan extends JPanel{
    @Override
    public void paint(Graphics g) {
        g.fill3DRect(10,10,5,10,true);
    }
}
