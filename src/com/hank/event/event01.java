package com.hank.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class event01 extends JFrame{
    mypenel penel=null;
    public static void main(String[] args) {
        event01 event01 = new event01();
    }
    event01(){
        penel=new mypenel();
        this.add(penel);
        this.setSize(500,500);
        this.addKeyListener(penel);//要让Jframeu对象可以监听键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
class mypenel extends JPanel implements KeyListener {
    int x=10;//物体左上角的x坐标
    int y=10;//物体左上角的y坐标
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,100,100);
    }

    @Override
    public void keyTyped(KeyEvent e) {//监听字符输入

    }

    @Override
    public void keyPressed(KeyEvent e) {//当某个键按下的时候出发
//        System.out.println((char) e.getKeyCode()+"被按下");
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                y-=2;
                break;
            case KeyEvent.VK_DOWN:
                y+=2;
                break;
            case KeyEvent.VK_LEFT:
                x-=2;
                break;
            case KeyEvent.VK_RIGHT:
                x+=2;
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {//当某个键释放了触发

    }
}