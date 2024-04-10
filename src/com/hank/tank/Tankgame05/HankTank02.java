package com.hank.tank.Tankgame05;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;

public class HankTank02 extends JFrame{
    Mypanel mypanel=null;

    public static void main(String[] args) throws IOException {
        System.out.println("请问是否要继续游戏？（1）");
        HankTank02 hankTank01 = new HankTank02();
    }
    public HankTank02()throws IOException {
        Scanner scanner = new Scanner(System.in);
        int key=scanner.nextInt();
        mypanel = new Mypanel(key);
            this.add(mypanel);
            Thread thread = new Thread(mypanel);//重要！！！！要让画板时时刻刻都刷新就将画板也变成一个线程让它一直repaint
            thread.start();
            this.setSize(1300,750);
            this.addKeyListener(mypanel);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public synchronized void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(HankTank02.this, "确定要关闭游戏吗？", "确认关闭", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        recorder.setEnemyTanks(mypanel.enemyVector);
                        recorder.keeprecord();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose(); // 关闭窗口
                    System.exit(0);
                }
            }
        });
        this.setVisible(true);
    }

}
