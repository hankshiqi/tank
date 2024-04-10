package com.hank.tank;

import javax.swing.*;

public class HankTank01 extends JFrame {
    Mypanel mypanel=null;

    public static void main(String[] args) {
        HankTank01 hankTank01 = new HankTank01();
    }
    public HankTank01(){
        mypanel = new Mypanel();
        this.add(mypanel);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
