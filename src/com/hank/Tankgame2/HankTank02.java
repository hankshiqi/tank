package com.hank.Tankgame2;

import javax.swing.*;

public class HankTank02 extends JFrame {
    Mypanel mypanel=null;

    public static void main(String[] args) {
        HankTank02 hankTank01 = new HankTank02();
    }
    public HankTank02(){
        mypanel = new Mypanel();
        this.add(mypanel);
        this.setSize(1000,750);
        this.addKeyListener(mypanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
