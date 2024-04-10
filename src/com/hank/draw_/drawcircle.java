package com.hank.draw_;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class drawcircle extends JFrame{//对应一个画画的窗口
    private Mypanel mypanel=null;
    public static void main(String[] args) {
        drawcircle drawcircle = new drawcircle();
        System.out.println("程序退出");
    }
    public drawcircle(){
        mypanel=new Mypanel();
        //把面板放入窗口
        this.add(mypanel);
        this.setSize(700,700);//设置画板
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//当点击窗口的小叉程序就完全退出
        this.setVisible(true);
    }
}
//1.定义一个面板.继承Jpanel，画图在面板上画

class Mypanel extends JPanel {//画板
    @Override
    public void paint(Graphics g) {//窗口大小发生变化就调用，初始Mypannel也会直接调用
        //g类似于画笔
        super.paint(g);//调用父类的有参方法，初始化
//        g.drawOval(10,10,20,20);
//        g.drawLine(10,10,100,100);
        g.setColor(Color.red);
//        g.fillRect(10,10,100,100);
//        g.fillOval(50,50,100,100);
//        try {
//            Image image = ImageIO.read(new File("C:\\Users\\Administrator\\IdeaProjects\\tank\\out\\production\\tank\\test01.png"));
//            g.drawImage(image, 10, 10, 582, 92, this);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        g.setFont(new Font("隶书",Font.BOLD,50));
        g.drawString("你好",100,100);
    }
}