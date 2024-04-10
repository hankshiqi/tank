package com.hank.tank.Tankgame4;

import javax.swing.*;

public class HankTank02 extends JFrame {
    Mypanel mypanel=null;

    public static void main(String[] args) {
        HankTank02 hankTank01 = new HankTank02();
    }
    public HankTank02(){
        mypanel = new Mypanel();
        this.add(mypanel);
        Thread thread = new Thread(mypanel);//重要！！！！要让画板时时刻刻都刷新就将画板也变成一个线程让它一直repaint
        thread.start();
        this.setSize(1000,750);
        this.addKeyListener(mypanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
